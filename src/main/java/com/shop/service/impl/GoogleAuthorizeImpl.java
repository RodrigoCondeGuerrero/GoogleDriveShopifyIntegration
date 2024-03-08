package com.shop.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.shop.ShopApplication;
import com.shop.service.GoogleAuthorize;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
public class GoogleAuthorizeImpl implements GoogleAuthorize
{
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String USER_CREDENTIALS_FILE_PATH = "/credentialOAuth.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    @Override
    public Credential getCredential() throws IOException
    {
        try
        {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            InputStream in = ShopApplication.class.getResourceAsStream(USER_CREDENTIALS_FILE_PATH);
            if (in == null)
            {
                throw new FileNotFoundException("Resource not found: " + USER_CREDENTIALS_FILE_PATH);
            }
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Arrays.asList(DriveScopes.DRIVE, SheetsScopes.SPREADSHEETS_READONLY))
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        } catch (GeneralSecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}