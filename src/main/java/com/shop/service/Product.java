package com.shop.service;

import com.shop.model.ShopifyProductResponse;

import java.util.List;

public interface Product
{
    List<ShopifyProductResponse> createProducts();

    List<ShopifyProductResponse> updateProducts();

    ShopifyProductResponse getProduct(String productId);
}
