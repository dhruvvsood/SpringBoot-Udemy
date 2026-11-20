package in.dhruvsood.chaiauth.services;

import in.dhruvsood.chaiauth.dtos.JobDto;
import in.dhruvsood.chaiauth.entities.JobEntity;
import in.dhruvsood.chaiauth.entities.User;
import in.dhruvsood.chaiauth.exceptions.ResourceNotFoundException;
import in.dhruvsood.chaiauth.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<JobDto> getAllJobs() {
        return jobRepository
                .findAll()
                .stream()
                .map(jobEntity -> modelMapper.map(jobEntity, JobDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobDto createNewJob(JobDto inputJob) {
        JobEntity jobEntity = modelMapper.map(inputJob, JobEntity.class);
        return modelMapper.map(jobRepository.save(jobEntity), JobDto.class);
    }

    @Override
    public JobDto getJobById(Long jobId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();

        log.info("User :: {}", user);

        JobEntity jobEntity = jobRepository
                .findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + jobId));
        return modelMapper.map(jobEntity, JobDto.class);
    }
}
