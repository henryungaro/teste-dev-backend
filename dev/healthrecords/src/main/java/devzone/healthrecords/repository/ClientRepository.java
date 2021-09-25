package devzone.healthrecords.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import devzone.healthrecords.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query
	(  value = "SELECT DISTINCT p.* " +
               "FROM proposition_01 p " +
               "JOIN profile_11 p11 ON p11.a01_id = p.a01_id " +
               "WHERE p11.a02_id = :userId AND " +
               "p.a20_id = :organizationId",
                nativeQuery = true)
	Optional<Client> findByName(String name);
}
