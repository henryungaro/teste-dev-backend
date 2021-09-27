package devzone.healthrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HealthrecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthrecordsApplication.class, args);
	}

}
