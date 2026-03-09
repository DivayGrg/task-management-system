package com.dway.task.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Comparable<Job> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private JobStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Isse table bante waqt automatic time set ho jayega
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public int compareTo(Job other) {
        return this.priority.compareTo(other.priority);
    }
}
