package com.example.dodast.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dodast.Exception.ImageUploadException;

import java.io.IOException;
import java.nio.file.*;

@Service
public class ImageService {

    private final String uploadDirectory = "uploads/";
    
    public String saveImage(MultipartFile file){
        try {
            Path directory = Paths.get(uploadDirectory);

            if(!Files.exists(directory)){
                Files.createDirectories(directory);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = directory.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );
            return "/uploads/" + fileName;

        } catch(IOException e){
            throw new ImageUploadException();
        }
    }
}