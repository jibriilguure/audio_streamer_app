package com.audiostreamer.app.repositories;

import com.audiostreamer.app.models.AudioTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioTrackRepository extends JpaRepository<AudioTrack, Long> {
    Optional<AudioTrack> findById(Long id);
    Optional<AudioTrack> findByTitle(String title);
}
