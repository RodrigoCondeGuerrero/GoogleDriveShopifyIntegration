package com.shop.service.impl;

import com.shop.service.ShopifyEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HttpEntityImpl implements ShopifyEntity
{
    @Value("${shopify.api.header}")
    private String shopifyHeader;

    @Value("${shopify.api.access.token}")
    private String token;

    @Override
    public HttpEntity getShopifyEntity()
    {
        return new HttpEntity(getHeaders());
    }

    @Override
    public HttpEntity getShopifyEntityWithBody(Object body)
    {
        return new HttpEntity(body, getHeaders());
    }

    private HttpHeaders getHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set(shopifyHeader, token);
        return headers;
    }
}