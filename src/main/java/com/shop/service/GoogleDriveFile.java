package com.shop.service;

import com.google.api.services.drive.model.FileList;

import java.util.List;

public interface GoogleDriveFile
{
    FileList getFileUrl(List<String> imageId);
}
