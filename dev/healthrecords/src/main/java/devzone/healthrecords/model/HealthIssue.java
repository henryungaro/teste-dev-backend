package devzone.healthrecords.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HealthIssue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private int degree;
	
	@ManyToOne
	@JoinColumn(name="client_id", nullable = false, updatable = true, insertable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Client client;
}