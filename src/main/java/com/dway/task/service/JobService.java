package com.dway.task.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.dway.task.model.Job;
import com.dway.task.model.JobStatus;
import com.dway.task.repository.JobRepository;

import jakarta.annotation.PostConstruct;

@Service
public class JobService {
    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private PriorityQueue<Job> jobQueue = new PriorityQueue<>();

    @PostConstruct
    public void initQueueFromDatabase() {
        List<Job> pendingJobs = jobRepository.findAll();
        for (Job job : pendingJobs) {
            if (job.getStatus().equals(JobStatus.PENDING)) {
                jobQueue.add(job);
            }
        }
        System.out.println("Queue initialized with " + jobQueue.size() + " pending jobs.");
    }

    public Job addJob(Job job) {
        job.setStatus(JobStatus.PENDING);
        Job savedJob = jobRepository.save(job);
        jobQueue.add(savedJob);
        return savedJob;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getNextJob() {
        return jobQueue.poll();
    }

    public void processNextJob() {
        // Sirf wahi task uthao jo PENDING hai aur priority sabse kam (top) hai
        Optional<Job> nextJob = jobRepository.findTopByStatusOrderByPriorityAsc(JobStatus.PENDING);

        if (nextJob.isPresent()) {
            Job job = nextJob.get();
            job.setStatus(JobStatus.RUNNING);
            jobRepository.save(job);
        } else {
            // Agar koi Pending task nahi hai, toh yahan kuch handle kar sakte ho (Optional)
            System.out.println("No pending tasks to process!");
        }
    }

    public void completeJob(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setStatus(JobStatus.COMPLETED); // Maan lo tumne COMPLETED enum banaya hai
            jobRepository.save(job);
        }
    }

    public List<Job> searchJobs(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }
}
