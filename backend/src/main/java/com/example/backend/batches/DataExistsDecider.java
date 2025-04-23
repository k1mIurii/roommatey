package com.example.backend.batches;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataExistsDecider implements JobExecutionDecider {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        String sql = "SELECT COUNT(*) FROM LOCATIONS;";
        Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        if (null != count && count > 0) {
            log.info("Locations already exists.");
            return new FlowExecutionStatus("SKIP");
        } else {
            log.info("Locations not exists, starting an import job.");
            return new FlowExecutionStatus("CONTINUE");
        }
    }
}
