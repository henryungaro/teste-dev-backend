package devzone.healthrecords.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devzone.healthrecords.model.Client;

@RestController
@RequestMapping("/register")
public class ClientController {

	private List<Client> person = new ArrayList<>();
	
	//Get a list of everyone
	@GetMapping("/list")
	public List<Client> list()
	{
		return person;
	}
	
//@GetMapping("/critical-list")
//sd = soma do grau dos problemas
//score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100
	
//@PostMapping("/create")
//@PostMapping("/update")
	
	//Search by person's name
	@GetMapping("/find/{name}")
	public Client FindByName(@PathVariable("name") String name) {
		Optional<Client> clientFind = person.stream().filter(client -> client.getName().toLowerCase().contains(name.toLowerCase())).findFirst();
		if(clientFind.isPresent()) {
			return clientFind.get();
		}
		else{
			return null;
		}
	}

}
