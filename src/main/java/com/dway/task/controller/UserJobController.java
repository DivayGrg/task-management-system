package com.dway.task.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dway.task.model.*;
import com.dway.task.service.*;

@RestController
@RequestMapping("/user/api/jobs")
public class UserJobController {
    private JobService jobService;

    public UserJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/list")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

}
