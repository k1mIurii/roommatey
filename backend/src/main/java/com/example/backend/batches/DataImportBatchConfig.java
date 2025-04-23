package com.example.backend.batches;

import com.example.backend.entities.Hobby;
import com.example.backend.entities.Location;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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
public class DataImportBatchConfig {

    @Bean
    public FlatFileItemReader<Location> locationsReader() {
        return new FlatFileItemReaderBuilder<Location>()
                .name("locations-reader")
                .resource(new ClassPathResource("locations.csv"))
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
    public JdbcBatchItemWriter<Location> locationsWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Location>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO locations (country, zip, city, state, latitude, longitude,created_at) VALUES (:country, :zip, :city, :state, :latitude, :longitude, :createdAt);")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          JdbcBatchItemWriter<Location> locationsWriter) {
        return new StepBuilder("locations-step", jobRepository)
                .<Location, Location> chunk(100, transactionManager)
                .reader(locationsReader())
                .writer(locationsWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Hobby> hobbiesReader() {
        return new FlatFileItemReaderBuilder<Hobby>()
                .name("hobbiesReader")
                .resource(new ClassPathResource("hobbies.csv"))
                .delimited()
                .names("Rank", "Hobby")
                .linesToSkip(1)
                .fieldSetMapper(fieldSet -> {
                    Hobby hobby = new Hobby();
                    hobby.setTitle(fieldSet.readString("Hobby"));
                    return hobby;
                })
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Hobby> hobbiesWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Hobby>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO hobbies (title) VALUES (:title);")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step secondStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           JdbcBatchItemWriter<Hobby> hobbiesWriter) {
        return new StepBuilder("hobbies-step", jobRepository)
                .<Hobby, Hobby> chunk(100, transactionManager)
                .reader(hobbiesReader())
                .writer(hobbiesWriter)
                .build();
    }

    @Bean
    public Job importDataJob(JobRepository jobRepository,
                             JobExecutionListener listener,
                             Step firstStep,
                             Step secondStep,
                             JobExecutionDecider decider) {
        return new JobBuilder("importDataJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(decider).on("SKIP").end()
                .from(decider).on("CONTINUE").to(firstStep)
                .next(secondStep)
                .end()
                .build();
    }
}
