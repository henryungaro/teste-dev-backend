package devzone.healthrecords.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devzone.healthrecords.model.Client;
import devzone.healthrecords.repository.ClientRepository;

@RestController
@RequestMapping("/register")
public class ClientController {
	
	@Autowired
	ClientRepository repository;
	
	//Get a list of everyone
	@GetMapping("/list")
	public List<Client> list()
	{
		return repository.findAll();
	}
	
//@GetMapping("/critical-list")
//sd = soma do grau dos problemas
//score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100
	
	@PostMapping("/create")
	Client newSection(@RequestBody Client newClient) {
        return repository.save(newClient);
    }
	
//@PostMapping("/update")
	
	//Search by person's id
	@GetMapping("/find/{id}")
	public Optional<Client> findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	//Search by person's name
	@GetMapping("/findName/{name}")
	public Optional<Client> findByName(@PathVariable("name") String name) {
		return repository.findByName(name);
	}

}
