package com.shop.service.impl;

import com.shop.model.ShopifyCollectRequest;
import com.shop.service.Collection;
import com.shop.service.GoogleSheet;
import com.shop.service.ShopifyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static com.shop.util.ShopConstants.COLLECTIONS;

@Service
public class CollectionImpl implements Collection
{
    @Autowired
    ShopifyEntity httpEntityInterface;

    @Autowired
    GoogleSheet googleSheets;

    @Value(("${shopify.api.url.collection.add.product}"))
    private String apiCollectionAddProductUrl;


    @Override
    public boolean addProductToCollection(String productId, String sku)
    {
        List<List<Object>> fileContentFields = googleSheets.getSheetData("Fields!A2:L");
        List<Object> fileContentFieldsFiltered = fileContentFields.stream()
                .filter(row -> row.get(0).equals(sku))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        String collectionName = COLLECTIONS.get(fileContentFieldsFiltered.get(11).toString());
        ShopifyCollectRequest collectRequest = new ShopifyCollectRequest();
        collectRequest.setCollect(new ShopifyCollectRequest.Collect(productId, collectionName));
        ResponseEntity<Object> response = new RestTemplate()
                .exchange(apiCollectionAddProductUrl, HttpMethod.POST, httpEntityInterface.getShopifyEntityWithBody(collectRequest), Object.class);

        return response.getStatusCode().equals(HttpStatus.CREATED);
    }
}