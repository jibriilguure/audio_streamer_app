package com.audiostreamer.app.repositories;

import com.audiostreamer.app.models.AudioTrack;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioTrackRepository extends JpaRepository<AudioTrack, Long> {
    // You can add custom query methods later if needed

    @Override
    Optional<AudioTrack> findById(Long aLong);
    Optional<AudioTrack> findByTitle(String title);

}
