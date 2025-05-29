package com.audiostreamer.app.services;

import com.audiostreamer.app.models.AudioTrack;
import com.audiostreamer.app.repositories.AudioTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class AudioUploadService {

    @Autowired
    private AudioTrackRepository audioTrackRepository;

    public AudioTrack uploadTrack(String title, String artist, MultipartFile file, String coverImageUrl) {
        // 🔁 Later: upload file to S3 and get back a public URL
        String fakeUrl = "https://your-s3-url.com/" + file.getOriginalFilename();

        AudioTrack track = AudioTrack.builder()
                .title(title)
                .artist(artist)
                .fileUrl(fakeUrl)
                .coverImageUrl(coverImageUrl)
                .uploadedAt(LocalDateTime.now())
                .build();

        return audioTrackRepository.save(track);
    }
}
