package in.dhruvsood.chaiauth.services;

import in.dhruvsood.chaiauth.dtos.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> getAllJobs();

    JobDto createNewJob(JobDto inputJob);

    JobDto getJobById(Long JobId);
}
