package com.yagmur.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {


    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    public String storeFile(MultipartFile file) throws IOException {
        System.out.println("test" + System.getProperty("user.dir") );
        if (file.isEmpty()) {
            throw new IOException("Dosya boş!");
        }

        // Klasörü kontrol et, yoksa oluştur
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Benzersiz bir dosya adı oluştur ve kaydet
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Tam URL'yi döndür
        return "http://localhost:1416/uploads/" + fileName;
    }

}