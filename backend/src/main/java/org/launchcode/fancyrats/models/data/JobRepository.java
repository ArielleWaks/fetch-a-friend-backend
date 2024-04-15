package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
