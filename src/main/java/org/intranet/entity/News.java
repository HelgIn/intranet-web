package org.intranet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class News extends ParentEntity {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private LocalDateTime date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @PrePersist
    void date() {
        this.date = LocalDateTime.now();
    }
}
