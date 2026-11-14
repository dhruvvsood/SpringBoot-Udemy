package in.dhruvsood.chaiauth.controllers;

import in.dhruvsood.chaiauth.dtos.JobDto;
import in.dhruvsood.chaiauth.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public JobDto getJobById(@PathVariable Long jobId) {
        return jobService.getJobById(jobId);
    }

    @PostMapping
    public JobDto createNewPost(@RequestBody JobDto inputJob) {
        return jobService.createNewJob(inputJob);
    }
}
