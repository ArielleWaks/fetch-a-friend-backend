package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
}
