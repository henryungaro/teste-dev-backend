package devzone.healthrecords.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devzone.healthrecords.model.HealthIssue;
import devzone.healthrecords.repository.HealthIssueRepository;

@RestController
@RequestMapping("/issues")
public class HealthIssueController {

	@Autowired
	HealthIssueRepository repository;
	
	//Get a list of everyone
	@GetMapping("/list")
	public List<HealthIssue> list()
	{
		return repository.findAll();
	}

	@PostMapping("/create")
	HealthIssue newHealthIssue(@RequestBody HealthIssue newIssue) {
        return repository.save(newIssue);
    }
	
	@PutMapping("/update/{id}")
	HealthIssue update(@RequestBody HealthIssue issue, @PathVariable Long id) {
		issue.setId(id);
        return repository.save(issue);
    }

	//Search by id
	@GetMapping("/find/{id}")
	public Optional<HealthIssue> findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	//Remove by id
	@DeleteMapping("/remove/{id}")
	public void deleteById(@PathVariable("id") Long id){
		repository.deleteById(id);
	}

}

