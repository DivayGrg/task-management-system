package com.dway.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dway.task.model.Job;
import com.dway.task.model.JobStatus;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCase(String title);

    Optional<Job> findTopByStatusOrderByPriorityAsc(JobStatus pending);
}