package devzone.healthrecords.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	private String name;
	private String birthDate;
	private String sex;
	//Register date will never change
	private String registerDate;
	private String updateDate;
	//This list will provide the health issue with its degree, since it could be more than one
	private List<Healthissue> health = new ArrayList<>();
}
