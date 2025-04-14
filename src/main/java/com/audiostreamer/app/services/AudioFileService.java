package com.audiostreamer.app.services;


import com.audiostreamer.app.models.AudioFile;
import com.audiostreamer.app.repositories.AudioFileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AudioFileService {

    @Autowired
    private AudioFileRepository audioFileRepository;

    public List<AudioFile> getAllAudioFiles() {
        return audioFileRepository.findAll();
    }

    public Optional<AudioFile> getAudioFileById(Long id) {
        return audioFileRepository.findById(id);
    }

    @Transactional
    public AudioFile saveAudioFile(AudioFile audioFile) {
        return audioFileRepository.save(audioFile);
    }

    public void deleteAudioFile(Long id) {
        audioFileRepository.deleteById(id);
    }
}
