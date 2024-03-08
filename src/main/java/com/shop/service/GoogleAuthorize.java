package com.shop.service;

import com.google.api.client.auth.oauth2.Credential;

import java.io.IOException;

public interface GoogleAuthorize
{
    Credential getCredential() throws IOException;
}