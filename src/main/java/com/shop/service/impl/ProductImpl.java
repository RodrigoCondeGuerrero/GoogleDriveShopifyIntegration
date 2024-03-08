package com.shop.service.impl;


import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.shop.model.ShopifyProductCreation;
import com.shop.model.ShopifyProductResponse;
import com.shop.model.ShopifyUpdateProductRequest;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.shop.util.ShopConstants.PRODUCT_CODES_TYPES;


@Service
public class ProductImpl implements Product
{
    private static final String SPREADSHEET_PRODUCT_ID_COLUMN = "C";
    private static final String SPREADSHEET_PUBLISHED_COLUMN = "D";
    private static final String VENDOR = "YOUR_VENDOR NAME";
    private static final String PRODUCT_FLAG_LOCKED = "L";
    private static final String PRODUCT_FLAG_PUBLISHED = "Y";
    private static final String GRAMS = "g";
    private static final String DRAFT_STATUS = "draft";
    private static final String SHEET_PIECES = "Pieces!";
    private static final String SHEET_FIELDS = "Fields!";
    private static final String SHEET_IMAGE_DATA = "ImageData!";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final int DEFAULT_IMAGE_POSITION = 99;
    public static final String HASH = "#";

    @Autowired
    ShopifyEntity httpEntityInterface;

    @Autowired
    GoogleSheet googleSheets;

    @Autowired
    Collection collectionInterface;

    @Autowired
    GoogleDriveFile imageInterface;

    @Value("${shopify.api.url.product}")
    private String apiProductUrl;

    @Value("${shopify.api.url.product.creation}")
    private String apiProductsCreationUrl;

    @Value("${shopify.api.url.product.update}")
    private String apiProductsUpdateUrl;

    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;


    @Override
    public List<ShopifyProductResponse> createProducts()
    {
        List<List<Object>> fileContent = googleSheets.getSheetData(SHEET_PIECES + "A2:D");
        List<List<Object>> fileContentFiltered;

        if (fileContent == null || fileContent.isEmpty())
        {
            System.out.println("No data found.");
            return null;
        } else
        {
            fileContentFiltered = filterProductListToCreate(fileContent);
        }

        List<ShopifyProductCreation> productToCreateList = getProductListToBeCreated(fileContentFiltered);
        List<ShopifyProductResponse> productCreatedList = new ArrayList<>();
        for (ShopifyProductCreation product : productToCreateList)
        {
            productCreatedList.add(createOnlineProduct(product));
        }

        addProductIdAndLockItInSpreadsheet(productCreatedList, fileContentFiltered);

        return productCreatedList;
    }

    @Override
    public ShopifyProductResponse getProduct(String productId)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ShopifyProductResponse> response = restTemplate.exchange(String.format(apiProductUrl, productId), HttpMethod.GET, httpEntityInterface.getShopifyEntity(), ShopifyProductResponse.class);

        return response.getBody();
    }

    @Override
    public List<ShopifyProductResponse> updateProducts()
    {
        List<List<Object>> fileContent = googleSheets.getSheetData(SHEET_PIECES + "A2:" + SPREADSHEET_PUBLISHED_COLUMN);
        List<List<Object>> fileContentFiltered = filterProductListToUpdate(fileContent);
        List<ShopifyUpdateProductRequest> productListToBeUpdated = getProductListToBeUpdated(fileContentFiltered);
        List<ShopifyProductResponse> updatedProducts = new ArrayList<>();
        for (int i = 0; i < productListToBeUpdated.size(); i++)
        {
            ShopifyProductResponse updatedProduct = updateOnlineProduct(String.format(apiProductsUpdateUrl, productListToBeUpdated.get(i).getProduct().getId()), productListToBeUpdated.get(i));
            updatedProducts.add(updatedProduct);
            collectionInterface.addProductToCollection(productListToBeUpdated.get(i).getProduct().getId(), productListToBeUpdated.get(i).getProduct().getVariants().get(0).getSku().split("-")[0]);
            tagProductAsPublished(fileContentFiltered.get(i));
        }

        return updatedProducts;
    }

    private void tagProductAsPublished(List<Object> product)
    {
        int rowNumber = Integer.parseInt(product.get(0).toString()) + 1;
        String cells = SHEET_PIECES + SPREADSHEET_PUBLISHED_COLUMN + rowNumber;
        List<List<Object>> values = new ArrayList<>(List.of(List.of(PRODUCT_FLAG_PUBLISHED)));
        try
        {
            googleSheets.writeDataToSheet(spreadsheetId, cells, values);
        } catch (IOException | GeneralSecurityException e)
        {
            e.printStackTrace();
        }
    }

    private List<ShopifyUpdateProductRequest> getProductListToBeUpdated(List<List<Object>> spreadshitProductList)
    {
        List<ShopifyUpdateProductRequest> productListToUpdate = new ArrayList<>();
        List<List<Object>> fileContentFields = googleSheets.getSheetData(SHEET_FIELDS + "A2:L");
        List<String> productIdList = spreadshitProductList.stream()
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0).toString())
                .collect(Collectors.toList());
        List<List<Object>> imagesAltTexts = googleSheets.getSheetData(SHEET_IMAGE_DATA + "A2:B");
        FileList imagesData = imageInterface.getFileUrl(productIdList);

        for (List<Object> spreadsheetProduct : spreadshitProductList)
        {
            String productSku = spreadsheetProduct.get(1).toString();
            List<Object> productData = fileContentFields.stream().filter(p -> p.contains(productSku)).collect(Collectors.toList()).get(0);
            if (!productData.isEmpty())
            {
                String productUID = spreadsheetProduct.get(0).toString();
                ShopifyUpdateProductRequest productToUpdate = new ShopifyUpdateProductRequest();
                ShopifyUpdateProductRequest.Product product = new ShopifyUpdateProductRequest.Product();
                List<Object> productAltTexts = imagesAltTexts.stream()
                        .filter(row -> row.get(0).equals(productData.get(11)))
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
                product.setImages(getProductImages(productUID, imagesData, productAltTexts));

                ShopifyUpdateProductRequest.Variant variant = new ShopifyUpdateProductRequest.Variant();
                variant.setSku(productSku + "-" + productUID);
                variant.setWeight(Double.parseDouble(productData.get(1).toString()));
                variant.setWeight_unit(GRAMS);
                variant.setPrice(new BigDecimal(productData.get(8).toString()));
                variant.setInventory_quantity(1);
                product.setVariants(new ArrayList<>(List.of(variant)));

                product.setId(spreadsheetProduct.get(2).toString());
                product.setTitle(String.format(productData.get(2).toString(), HASH + productUID));
                product.setBody_html(productData.get(10).toString());
                product.setTags(productData.get(3).toString());

                productToUpdate.setProduct(product);
                productListToUpdate.add(productToUpdate);
            } else
            {
                System.out.println(productSku + " not found on " + SHEET_FIELDS);
            }
        }

        return productListToUpdate;
    }

    private ArrayList<ShopifyUpdateProductRequest.Image> getProductImages(String productUID, FileList imagesData, List<Object> productAltTexts)
    {
        List<File> filteredImagesData = imagesData.getFiles().stream()
                .filter(file -> file.getName().split("-")[0].equals(productUID))
                .collect(Collectors.toList());
        String[] imageAltText = productAltTexts.get(1).toString().split("\n");
        ArrayList<ShopifyUpdateProductRequest.Image> productImages = new ArrayList<>();
        for (File image : filteredImagesData)
        {
            ShopifyUpdateProductRequest.Image productImage = new ShopifyUpdateProductRequest.Image();
            productImage.setSrc(image.getWebContentLink());
            int imagePosition = getImagePosition(image);
            productImage.setPosition(imagePosition);
            if (imagePosition <= imageAltText.length)
            {
                productImage.setAlt(imageAltText[imagePosition - 1]);
            } else
            {
                productImage.setAlt(imageAltText[imageAltText.length - 1]);
                System.out.println(ANSI_YELLOW + "Product #" + productUID + " contains a default alt text, because the original image position on the image's name it's not valid to get the correct alt text" + ANSI_RESET);
            }
            productImages.add(productImage);
        }

        return productImages;
    }

    private int getImagePosition(File file)
    {
        String[] fileSplit = file.getName().split("-");
        if (fileSplit.length == 2)
        {
            return Integer.parseInt(fileSplit[1]);
        }
        return DEFAULT_IMAGE_POSITION;
    }

    private List<List<Object>> filterProductListToCreate(List<List<Object>> fileContent)
    {
        return fileContent.stream()
                .filter(row -> row.size() == 2 || (row.size() == 4 && row.get(3).toString().isEmpty()))
                .collect(Collectors.toList());
    }

    private List<List<Object>> filterProductListToUpdate(List<List<Object>> fileContent)
    {
        return fileContent.stream()
                .filter(row -> !row.isEmpty() && row.size() > 2)
                .filter(row -> PRODUCT_FLAG_LOCKED.equals(row.get(3).toString()))
                .filter(row -> !row.get(2).toString().isEmpty())
                .collect(Collectors.toList());
    }

    private List<ShopifyProductCreation> getProductListToBeCreated(List<List<Object>> fileContent)
    {
        List<ShopifyProductCreation> productList = new ArrayList<>();
        for (List<Object> row : fileContent)
        {
            String productType = PRODUCT_CODES_TYPES.get(row.get(1).toString().substring(0, 2));
            ShopifyProductCreation product = new ShopifyProductCreation();
            product.setProduct(new ShopifyProductCreation.Product());
            String title = HASH + row.get(0).toString() + " title";

            product.getProduct().setTitle(title);
            product.getProduct().setBody_html("Product description");
            product.getProduct().setVendor(VENDOR);
            product.getProduct().setProduct_type(productType);
            product.getProduct().setStatus(DRAFT_STATUS);
            productList.add(product);
        }
        return productList;
    }

    private ShopifyProductResponse createOnlineProduct(ShopifyProductCreation product)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ShopifyProductResponse> response = restTemplate.postForEntity(apiProductsCreationUrl, httpEntityInterface.getShopifyEntityWithBody(product), ShopifyProductResponse.class);

        return response.getBody();
    }

    private ShopifyProductResponse updateOnlineProduct(String apiEndPoint, ShopifyUpdateProductRequest product)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ShopifyProductResponse> response = restTemplate.exchange(apiEndPoint, HttpMethod.PUT, httpEntityInterface.getShopifyEntityWithBody(product), ShopifyProductResponse.class);

        return response.getBody();
    }

    private void addProductIdAndLockItInSpreadsheet(List<ShopifyProductResponse> productCreatedList, List<List<Object>> fileContentFiltered)
    {
        try
        {
            for (int i = 0; i < fileContentFiltered.size(); i++)
            {
                int rowNumber = Integer.parseInt(fileContentFiltered.get(i).get(0).toString()) + 1;
                String cells = SHEET_PIECES + SPREADSHEET_PRODUCT_ID_COLUMN + rowNumber + ":" + SPREADSHEET_PUBLISHED_COLUMN;
                List<List<Object>> values = new ArrayList<>(List.of(Arrays.asList(productCreatedList.get(i).getProduct().getId(), "L")));
                googleSheets.writeDataToSheet(spreadsheetId, cells, values);
            }
        } catch (IOException | GeneralSecurityException e)
        {
            e.printStackTrace();
        }
    }
}