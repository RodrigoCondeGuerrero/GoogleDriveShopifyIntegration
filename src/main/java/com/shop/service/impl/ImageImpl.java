package com.shop.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.shop.service.GoogleAuthorize;
import com.shop.service.GoogleDriveFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


@Service
public class ImageImpl implements GoogleDriveFile
{
    @Autowired
    GoogleAuthorize googleAuthorize;

    @Value("${google.drive.images.folder.id}")
    private String folderId;

    @Value("${google.application.name}")
    private String applicationName;


    @Override
    public FileList getFileUrl(List<String> imagesId)
    {
        String pageToken = null;
        FileList result;
        try
        {
            do
            {
                Credential credential = googleAuthorize.getCredential();
                Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), credential)
                        .setApplicationName(applicationName)
                        .build();
                result = service.files().list()
                        .setQ("'" + folderId + "' in parents")
                        .setFields("files(id, name,webContentLink)")
                        .setPageToken(pageToken)
                        .execute();

                for (File file : result.getFiles())
                {
                    file.setName(FilenameUtils.removeExtension(file.getName()));
                }
                pageToken = result.getNextPageToken();
            } while (pageToken != null);

            return result;

        } catch (IOException | GeneralSecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}