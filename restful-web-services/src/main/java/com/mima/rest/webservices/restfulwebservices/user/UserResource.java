package com.mima.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
