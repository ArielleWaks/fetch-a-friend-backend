package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
