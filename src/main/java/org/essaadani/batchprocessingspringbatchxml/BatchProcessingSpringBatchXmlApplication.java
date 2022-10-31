package org.essaadani.batchprocessingspringbatchxml;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BatchProcessingSpringBatchXmlApplication {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessingSpringBatchXmlApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
                Map<String, JobParameter> parameterMap = new HashMap<>();
                parameterMap.put("time", new JobParameter(System.currentTimeMillis()));

                JobParameters jobParameters = new JobParameters(parameterMap);

                JobExecution jobExecution = jobLauncher.run(job, jobParameters);

                while(jobExecution.isRunning()){
                    System.out.println("Job is loading data...");
                }

            System.out.println(jobExecution.getStatus());;
        };
    }
}
