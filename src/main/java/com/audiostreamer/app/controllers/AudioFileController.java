package com.audiostreamer.app.controllers;


import com.audiostreamer.app.models.AudioFile;
import com.audiostreamer.app.services.AudioFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/audiofiles")
public class AudioFileController {


    @Autowired
    private AudioFileService audioFileService;

    @GetMapping
    public List<AudioFile> getAllAudioFiles() {
        return audioFileService.getAllAudioFiles();
    }

    @GetMapping("/{id}")
    public Optional<AudioFile> getAudioFileById(@PathVariable Long id) {
        return audioFileService.getAudioFileById(id);
    }

    @PostMapping
    public AudioFile uploadAudioFile(@RequestBody AudioFile audioFile) {
        return audioFileService.saveAudioFile(audioFile);
    }

    @DeleteMapping("/{id}")
    public String deleteAudioFile(@PathVariable Long id) {
        audioFileService.deleteAudioFile(id);
        return "Audio file deleted successfully";
    }
}
