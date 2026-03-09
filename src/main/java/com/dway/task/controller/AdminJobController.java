package com.dway.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import com.dway.task.service.JobService;
import com.dway.task.model.Job;

@RestController
@RequestMapping("/admin/api/jobs")
public class AdminJobController {
    private JobService jobService;

    public AdminJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/add")
    public Job addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    // @GetMapping("/process-next")
    // public Job processNext() {
    // return jobService.getNextJob();
    // }

}
