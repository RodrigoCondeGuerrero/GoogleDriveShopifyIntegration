package com.shop.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSheet
{
    List<List<Object>> getSheetData(String range);

    void writeDataToSheet(String spreadsheetId, String range, List<List<Object>> values) throws IOException, GeneralSecurityException;
}