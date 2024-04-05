package org.launchcode.fancyrats.security.services;

import org.launchcode.fancyrats.models.FileDetails;
import org.launchcode.fancyrats.payload.response.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    public FileUploadResponse uploadFile(MultipartFile file,
                                         String uploaderName) throws IOException;

    public Resource fetchFileAsResource(String fileName) throws FileNotFoundException;

    public List<FileDetails> getAllFiles();
}

