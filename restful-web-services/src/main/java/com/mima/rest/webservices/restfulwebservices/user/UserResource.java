package com.mima.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	private boolean result;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findUser(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+id);
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/mutant/")
	public boolean isMutant(@RequestBody Dna dna) {
		System.out.println("Entro al metodo:::");
		String[] arrDna = dna.getDna();
		boolean resultBack = false;
		System.out.println("arrDna:::"+arrDna.length);
		resultBack = this.service.isMutant(arrDna);
		
		System.out.println("ResultBack:::"+resultBack);
		
		if(!resultBack)
			throw new DnaException("");
			
		
		else {
			System.out.println("resultBack:::"+resultBack);
			//return resultBack;
		}
		System.out.println("ResultBack:::"+resultBack);
		return resultBack;
			
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
