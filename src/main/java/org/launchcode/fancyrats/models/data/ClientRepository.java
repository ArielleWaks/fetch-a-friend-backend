package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByZipCode(String zipCode);
//    List<Client> findByZipCode(String zipCode);
}
