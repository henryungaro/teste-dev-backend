package devzone.healthrecords.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.lang.Double;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devzone.healthrecords.model.Client;
import devzone.healthrecords.model.ClientRisk;
import devzone.healthrecords.repository.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
	Date date = new Date();
	private static double e = 2.718281828459045235360287;
	Comparator<ClientRisk> compareByScore = (ClientRisk o1, ClientRisk o2) -> Double.compare(o1.getScore(), o2.getScore());

			
	
	@Autowired
	ClientRepository repository;
	
	//Get a list of everyone
	@GetMapping("/list")
	public List<Client> list()
	{
		return repository.findAll();
	}
	
	@GetMapping("/criticalList")
	public List<ClientRisk> findClientRisk()
	{

		List<Client> listClient = repository.findAll();
	    List<ClientRisk> listRisk = new ArrayList<>();
	    Client auxClient = new Client();
	    if(listClient.size() < 10)
	    {
		    for(int i = 0; i < listClient.size(); i++)
		    {
		    ClientRisk auxRisk = new ClientRisk();
		    auxClient = listClient.get(i);
		    auxRisk.setId(auxClient.getId());
		    auxRisk.setName(auxClient.getName());
		    auxRisk.setScore((1 / (Math.pow( 1 + e,-(-2.8 + repository.sumDegree(auxRisk.getId())))))* 100 );
		    listRisk.add(auxRisk);
		    }
	    }
	    else
	    {
	    	for(int i = 0; i < 9; i++)
		    {
		    ClientRisk auxRisk = new ClientRisk();
		    auxClient = listClient.get(i);
		    auxRisk.setId(auxClient.getId());
		    auxRisk.setName(auxClient.getName());
		    auxRisk.setScore((1 / (Math.pow( 1 + e,-(-2.8 + repository.sumDegree(auxRisk.getId())))))* 100 );
		    listRisk.add(auxRisk);
		    }
	    }
	    Collections.sort(listRisk, compareByScore.reversed());
	    return listRisk;
	}
	
//sd = repository,sumDegree(id)
//e = 2.718281828459045235360287
//(1 / (1 + e ^ -(-2.8 + sd ))) * 100;
//(1 / (Math.pow( 1 + e,-(-2.8 + sd ))) )* 100
//(1/(1+Math.exp(2.8-sd))) * 100;
	
	@PostMapping("/create")
	Client newClient(@RequestBody Client newClient) {
		newClient.setRegisterDate(dateFormat.format(date));
        return repository.save(newClient);
    }
	
	@PutMapping("/update/{id}")
    Client update(@RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        client.setRegisterDate(repository.findDateById(id));
        client.setUpdateDate(dateFormat.format(date));
        return repository.save(client);
    }


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
	
	//Remove by id
	@DeleteMapping("/remove/{id}")
	public void deleteById(@PathVariable("id") Long id){
		repository.deleteById(id);
	}

}
