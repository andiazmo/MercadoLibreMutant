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
	public void isMutant(String[] dna) {
		//Saber el tamaño del arreglo
		//Saber el tamaño del String
		//Crear areglo apartir de los valores encontrados
		//agregar los string a el arreglo
		int sizeArr = dna.length;
		char[] arrDna = new char[sizeArr*sizeArr];
		
		for(int i = 0; i < arrDna.length; i++ ) {
			for(int j = 0; j < sizeArr; j++) {
				//arrDna[i] = 
			}
			
		}
		
		
		
		
	}
	
	public static void bubbleSort(int[] arr){
		int n = arr.length;
		
		for(int i=0; i<n-1; i++){
			for(int j=1; j<n; j++){
				if(arr[i] > arr[j]) {
					int max = arr[i];
					arr[i] =  arr[j];
					arr[j] = max;
				}
			}
		}
		
	}
}
