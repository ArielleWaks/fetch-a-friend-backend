package org.launch_code.fetch_a_friend.security.services;

import org.launch_code.fetch_a_friend.exception.FileNotSupportedException;
import org.launch_code.fetch_a_friend.models.FileDetails;
import org.launch_code.fetch_a_friend.models.data.FileDetailsRepository;
import org.launch_code.fetch_a_friend.payload.response.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
//
//
//
//    @Autowired
//    private FileDetailsRepository fileDetailsRepository;
//
//    private final Path UPLOAD_PATH =
//            Paths.get(new ClassPathResource("").getFile().getAbsolutePath()
//                    + File.separator + "static"
//                    + File.separator + "image");
//
//    public FileUploadServiceImpl() throws IOException {
//        System.out.println("UPLOAD_PATH: " + UPLOAD_PATH);
//    }
//
//    @Override
//    public FileUploadResponse uploadFile(MultipartFile file,
//                                         String uploaderName, String description) throws IOException {
//        if (!Files.exists(UPLOAD_PATH)) {
//            Files.createDirectories(UPLOAD_PATH);
//        }
//
//        // file format validation
//        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
//            throw new FileNotSupportedException("only .jpeg and .png images are " + "supported");
//        }
//
//        String timeStampedFileName = new SimpleDateFormat("ssmmHHddMMyyyy")
//                .format(new Date()) + "_" + file.getOriginalFilename();
//
//        Path filePath = UPLOAD_PATH.resolve(timeStampedFileName);
//        Files.copy(file.getInputStream(), filePath);
//
//        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/image/").path(timeStampedFileName).toUriString();
//
//        String fileDownloadUri =
//                ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("/file/download/").path(timeStampedFileName).toUriString();
//
//        FileDetails fileDetails = new FileDetails(file.getOriginalFilename(),
//                fileUri,
//                fileDownloadUri, file.getSize(), uploaderName, description );
//
//        this.fileDetailsRepository.save(fileDetails);
//
//        FileUploadResponse fileUploadResponse =
//                new FileUploadResponse(fileDetails.getId(),
//                        file.getOriginalFilename(), fileUri, fileDownloadUri,
//                        file.getSize(),
//                        uploaderName, description);
//
//        return fileUploadResponse;
//    }
//
//    @Override
//    public Resource fetchFileAsResource(String fileName) throws FileNotFoundException {
//
//        try {
//            Path filePath = UPLOAD_PATH.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new FileNotFoundException("File not found " + fileName);
//        }
//    }
//
//    @Override
//    public List<FileDetails> getAllFiles() {
//        return this.fileDetailsRepository.findAll();
//    }

}

