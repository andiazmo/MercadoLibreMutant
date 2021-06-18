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
	
	@PostMapping("/mutant/")
	public boolean isMutant(@RequestBody Dna dna) {
		String[] arrDna = dna.getDna();
		boolean resultBack = false;
		resultBack = this.service.isMutant(arrDna);
		
		if(!resultBack)
			throw new DnaException("");
		
		return resultBack;
	}
}
