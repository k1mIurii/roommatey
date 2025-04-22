package com.example.backend.configs;

import com.example.backend.entities.Location;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Location> reader() {
        return new FlatFileItemReaderBuilder<Location>()
                .name("usZipCodesReader")
                .resource(new ClassPathResource("uszipcodes.csv"))
                .delimited()
                .names("country", "zip", "city", "state", "state abbreviation", "county", "metadata", "latitude", "longitude")
                .linesToSkip(1)
                .fieldSetMapper(fieldSet -> {
                    Location location = new Location();
                    location.setCountry(fieldSet.readString("country"));
                    location.setZip(fieldSet.readString("zip"));
                    location.setCity(fieldSet.readString("city"));
                    location.setState(fieldSet.readString("state"));
                    location.setLatitude(fieldSet.readDouble("latitude"));
                    location.setLongitude(fieldSet.readDouble("longitude"));
                    location.setCreatedAt(LocalDateTime.now());
                    return location;
                })
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Location> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Location>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO locations (country, zip, city, state, latitude, longitude,created_at) VALUES (:country, :zip, :city, :state, :latitude, :longitude, :createdAt);")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importZipCodesJob(JobRepository jobRepository,
                                 JobExecutionListener listener,
                                 Step step) {
        return new JobBuilder("importZipCodesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          JdbcBatchItemWriter<Location> writer) {
        return new StepBuilder("firstStep", jobRepository)
                .<Location, Location> chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemProcessor<Location, Location> processor() {
        return new LocationItemProcessor();
    }
}
