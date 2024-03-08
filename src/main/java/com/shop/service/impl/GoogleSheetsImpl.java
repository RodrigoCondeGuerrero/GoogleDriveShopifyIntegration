package com.shop.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.shop.service.GoogleAuthorize;
import com.shop.service.GoogleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


@Service
public class GoogleSheetsImpl implements GoogleSheet
{
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String SERVICE_ACCOUNT_CREDENTIAL = "src/main/resources/credentialServiceAccount.json";

    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;

    @Value("${google.application.name}")
    private String applicationName;

    @Autowired
    GoogleAuthorize googleAuthorize;

    @Override
    public List<List<Object>> getSheetData(String range)
    {
        final NetHttpTransport HTTP_TRANSPORT;
        try
        {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleAuthorize.getCredential())
                    .setApplicationName(applicationName)
                    .build();

            return service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute()
                    .getValues();
        } catch (GeneralSecurityException | IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeDataToSheet(String spreadsheetId, String range, List<List<Object>> values) throws IOException, GeneralSecurityException
    {
        Sheets sheetsService = createSheetsService();
        sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, new ValueRange().setValues(values))
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    private Sheets createSheetsService() throws IOException, GeneralSecurityException
    {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_CREDENTIAL))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(applicationName)
                .build();
    }
}