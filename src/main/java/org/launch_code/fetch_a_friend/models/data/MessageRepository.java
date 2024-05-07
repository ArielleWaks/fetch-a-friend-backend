package org.launch_code.fetch_a_friend.models.data;

import org.launch_code.fetch_a_friend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
