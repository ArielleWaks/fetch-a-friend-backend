package org.launch_code.fetch_a_friend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.launch_code.fetch_a_friend.exception.FileNotSupportedException;
import org.launch_code.fetch_a_friend.models.FileDetails;
import org.launch_code.fetch_a_friend.payload.response.FileUploadResponse;
import org.launch_code.fetch_a_friend.security.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "file")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {
//
//    @Autowired
//    private FileUploadService fileUploadService;
//
//    @GetMapping
//    @ResponseStatus(code = HttpStatus.OK)
//    @PreAuthorize("hasRole('MODERATOR')")
//    public List<FileDetails> getAllFiles() {
//        return this.fileUploadService.getAllFiles();
//    }
//
//    @PostMapping(value = "/upload")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public ResponseEntity<Object> uploadFiles(@RequestParam("name") String name
//            , @RequestParam("files") MultipartFile[] files, @RequestParam("description") String description) {
//
//        try {
//            List<FileUploadResponse> fileUploadResponses =
//                    Arrays.stream(files).map(file -> {
//                        try {
//                            return fileUploadService.uploadFile(file, name, description);
//                        } catch (IOException e) {
//                            throw new UncheckedIOException(e);
//                        }
//                    }).collect(Collectors.toList());
//
//            return new ResponseEntity<>(fileUploadResponses, HttpStatus.OK);
//        } catch (UncheckedIOException e) {
//            return new ResponseEntity<>(e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (FileNotSupportedException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/download/{fileName:.+}")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public ResponseEntity<Object> downloadFile(@PathVariable String fileName,
//                                               HttpServletRequest request) {
//
//        try {
//            Resource resource = this.fileUploadService.fetchFileAsResource(fileName);
//            String contentType =
//                    request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (IOException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}

