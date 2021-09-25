package devzone.healthrecords.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String birthDate;
	private String sex;
	//Register date will never change
	private String registerDate;
	private String updateDate;
	//This list will provide the health issue with its degree, since it could be more than one
	
	@OneToMany(mappedBy = "client", targetEntity = HealthIssue.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HealthIssue> health = new ArrayList<>();
}
