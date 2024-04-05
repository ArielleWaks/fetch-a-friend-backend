package org.launchcode.fancyrats.backend.models;

import org.junit.jupiter.api.Test;
import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.JobStatus;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ActiveProfiles(value = "test")
@DataJpaTest
public class JobTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void givenNewJob_whenSaveIsCalled_thenRecordSavedToDatabase() {
        Job job = new Job(
                63108,
                LocalDate.of(2024, 3,15),
                LocalDate.of(2024, 3, 20),
                LocalDate.now(), 18, 5,
                "Job Test",
                petName, petType, petNumber, new User(),
                new User(),
                JobStatus.STATUS_OPEN
        );
        Job saveJob = jobRepository.save(job);
        assertThat(entityManager.find(Job.class, saveJob.getId()) ).isEqualTo(job);
    }

    @Test
    public void givenSavedJob_whenUpdated_thenJobUpdatedInDatabase() {
        Job job = new Job(
                63108,
                LocalDate.of(2024, 3,15),
                LocalDate.of(2024, 3, 20),
                LocalDate.now(), 18, 5,
                "Job Test",
                petName, petType, petNumber, new User(),
                new User(),
                JobStatus.STATUS_OPEN
        );
        entityManager.persist(job);
        job.setDescription("Updated Job Test");
        jobRepository.save(job);
        assertThat(entityManager.find(Job.class, job.getId()).getDescription())
                .isEqualTo("Updated Job Test");

    }

    @Test
    void givenJobCreated_whenDelete_thenSuccess() {
        Job job = new Job(
                63108,
                LocalDate.of(2024, 3,15),
                LocalDate.of(2024, 3, 20),
                LocalDate.now(), 18, 5,
                "Job Test",
                petName, petType, petNumber, new User(),
                new User(),
                JobStatus.STATUS_OPEN
        );
        entityManager.persist(job);
        jobRepository.delete(job);
        assertThat(entityManager.find(Job.class, job.getId())).isNull();
    }

    @Test
    void givenJobCreated_whenFindById_returnsJob() {
        Job job = new Job(
                63108,
                LocalDate.of(2024, 3,15),
                LocalDate.of(2024, 3, 20),
                LocalDate.now(), 18, 5,
                "Job Test",
                petName, petType, petNumber, new User(),
                new User(),
                JobStatus.STATUS_OPEN
        );
        entityManager.persist(job);
        Optional<Job> retrivedJob = jobRepository.findById(job.getId());
        assertThat(retrivedJob).contains(job);
    }

}
