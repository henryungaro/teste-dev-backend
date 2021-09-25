package devzone.healthrecords.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import devzone.healthrecords.model.HealthIssue;

public interface HealthIssueRepository extends JpaRepository<HealthIssue, Long>{

	List<HealthIssue> findAllByClient(Long clientId); 
	
}
