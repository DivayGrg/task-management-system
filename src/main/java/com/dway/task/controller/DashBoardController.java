package com.dway.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.dway.task.model.Job;
import java.util.*;
import com.dway.task.service.JobService;

@Controller
public class DashBoardController {
    private JobService jobService;

    public DashBoardController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String home() {
        return "index"; // index.html load karega
    }

    @GetMapping("/admin/dashboard")
    public String admindashboard(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "dashboard";
    }

    @PostMapping("/admin/add-job-save")
    public String saveJobFromForm(@ModelAttribute("job") Job job) {
        jobService.addJob(job);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/api/jobs/add")
    public String showAddJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "add-job";
    }

    @GetMapping("/admin/process")
    public String processNextJob() {
        jobService.processNextJob();
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/complete/{id}")
    public String completeJob(@PathVariable Long id) {
        jobService.completeJob(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/user/dashboard")
    public String showUserDashboard(@RequestParam(required = false) String search, Model model) {
        List<Job> jobs;
        if (search != null && !search.isEmpty()) {
            jobs = jobService.searchJobs(search);
        } else {
            jobs = jobService.getAllJobs();
        }
        model.addAttribute("jobs", jobs);
        return "userdashboard";
    }

    @GetMapping("/admin/delete-job/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJobById(id);
        return "redirect:/admin/dashboard";
    }
}
