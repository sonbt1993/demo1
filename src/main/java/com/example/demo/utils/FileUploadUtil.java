package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }


//    @Value("${upload.path}")
//    private String path;
//
//    public void saveFile(MultipartFile file) throws IOException {
//
//        if (file.isEmpty()) {
//            throw new StorageException("Failed to store empty file");
//        }
//
//        String fileName = file.getOriginalFilename();
//        try {
//            var is = file.getInputStream();
//            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            var msg = String.format("Failed to store file %s", fileName);
//            throw new IOException(msg, e);
//        }
//    }
}
