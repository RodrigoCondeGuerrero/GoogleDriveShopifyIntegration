package com.shop.service;

import org.springframework.http.HttpEntity;

public interface ShopifyEntity
{
    HttpEntity getShopifyEntity();

    HttpEntity getShopifyEntityWithBody(Object body);
}
