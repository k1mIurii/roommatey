package com.example.backend.listeners;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JobCompletionListener implements JobExecutionListener {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job {} completed !!!", jobExecution.getJobInstance().getJobName());

            Integer records = this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM locations;", Integer.class);
            if (null != records) {
                log.info("Found {} location records in database.", records);
            } else {
                log.warn("No location records found in database after job completion.");
            }
        }
    }
}
