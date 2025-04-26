package com.audiostreamer.app.controllers;


import com.audiostreamer.app.services.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/files")
public class FileUploadController {


        private final S3Service s3Service;

        public FileUploadController(S3Service s3Service) {
            this.s3Service = s3Service;
        }

        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            try {
                String fileName = file.getOriginalFilename();
                s3Service.uploadFile(fileName, file);
                return ResponseEntity.ok("✅ File uploaded successfully: " + fileName);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("❌ Upload failed: " + e.getMessage());
            }
        }


}
