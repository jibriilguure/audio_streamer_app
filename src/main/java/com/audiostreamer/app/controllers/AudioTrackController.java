package com.audiostreamer.app.controllers;

import com.audiostreamer.app.models.AudioTrack;
import com.audiostreamer.app.services.AudioUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/audio")
public class AudioTrackController {

    @Autowired
    private AudioUploadService audioUploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AudioTrack> uploadTrack(
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "coverImageUrl", required = false) String coverImageUrl
    ) {
        AudioTrack savedTrack = audioUploadService.uploadTrack(title, artist, file, coverImageUrl);
        return ResponseEntity.ok(savedTrack);
    }
}
