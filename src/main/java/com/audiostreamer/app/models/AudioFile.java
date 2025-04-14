package com.audiostreamer.app.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audio_files")
public class AudioFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String fileUrl;  // Path or URL to the audio file

    @Column
    private String coverImageUrl; // Path or URL to the cover image

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    // Constructors
    public AudioFile() {}

    public AudioFile(String title, String artist, String fileUrl, String coverImageUrl) {
        this.title = title;
        this.artist = artist;
        this.fileUrl = fileUrl;
        this.coverImageUrl = coverImageUrl;
        this.uploadedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}

