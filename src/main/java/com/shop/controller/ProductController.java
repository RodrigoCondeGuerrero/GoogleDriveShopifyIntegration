package com.shop.controller;

import com.shop.model.ShopifyProductResponse;
import com.shop.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    Product productInterface;

    @GetMapping("/get")
    public ShopifyProductResponse getProduct(String productId)
    {
        return productInterface.getProduct(productId);
    }

    @GetMapping("/create")
    public List<ShopifyProductResponse> createProduct()
    {
        return productInterface.createProducts();
    }

    @GetMapping("/update")
    public List<ShopifyProductResponse> updateProduct()
    {
        return productInterface.updateProducts();
    }
}
