package com.audiostreamer.app.controllers;

import com.audiostreamer.app.models.AudioTrack;
import com.audiostreamer.app.repositories.AudioTrackRepository;
import com.audiostreamer.app.services.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audio")
public class AudioTrackController {

    private final S3Service s3Service;
    private final AudioTrackRepository audioTrackRepository;

    public AudioTrackController(S3Service s3Service, AudioTrackRepository audioTrackRepository) {
        this.s3Service = s3Service;
        this.audioTrackRepository = audioTrackRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAudio(
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("file") MultipartFile file,
            @RequestParam("coverImageUrl") String coverImageUrl) {
        try {
            String key = "tracks/" + file.getOriginalFilename();
            String fileUrl = s3Service.uploadFile(key, file);

            AudioTrack track = AudioTrack.builder()
                    .title(title)
                    .artist(artist)
                    .fileUrl(fileUrl)
                    .coverImageUrl(coverImageUrl)
                    .build();

            audioTrackRepository.save(track);

            return ResponseEntity.ok(track);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AudioTrack>> getAllTracks() {
        List<AudioTrack> tracks = audioTrackRepository.findAll();
        return ResponseEntity.ok(tracks);
    }
}
