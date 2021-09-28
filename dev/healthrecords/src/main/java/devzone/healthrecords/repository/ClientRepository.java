package devzone.healthrecords.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import devzone.healthrecords.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query
	( value ="SELECT * " +
             "FROM client c, health_issue h " +
             "WHERE c.id = h.client_id AND" +
             " c.name LIKE %:name% ",
                nativeQuery = true)
	Optional<Client> findByName(@Param("name") String name);
	
	@Query
	( value ="SELECT register_date "
			+"FROM client WHERE id = :id",
               nativeQuery = true)
	String findDateById(@Param("id") Long id);
	
	@Query
	(value= "SELECT SUM(degree) "
	+"FROM client c, health_issue h "
	+ "WHERE c.id = h.client_id AND c.id= :id"
	, nativeQuery = true)
	double sumDegree(@Param("id") Long id);

}
