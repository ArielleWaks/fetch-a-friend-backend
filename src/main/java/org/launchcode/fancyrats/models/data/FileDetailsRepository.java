package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FileDetailsRepository extends JpaRepository<FileDetails, Integer> {

}
