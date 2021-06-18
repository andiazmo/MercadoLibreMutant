package com.mima.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	private int countFor = 0;
	private int validColumns = 0;
	
	static {
		users.add(new User(1, "Anyelo Diaz M Msc", new Date()));
		users.add(new User(2, "Mairanela Ramirez Salazar", new Date()));
		users.add(new User(3, "M&M", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		
		return user;
	}
	
	public User findUser(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public boolean isMutant(String[] dna) {
		
		int sizeArr = dna.length;
		char[] arrDna = new char[sizeArr*sizeArr];
		int countRow = 0;
		int countColumn = 0;
		int countObl = 0;
		int result = 0;
				
		if(!this.validationStr(dna))
			return false;
		
		arrDna = this.createArray(dna);
		
		if(!this.validations(arrDna)) {
			return false;
		}
			
		for(int i = 0; i < arrDna.length; i++) {
			System.out.print(arrDna[i]);
		}
		
		for(int i = 0; i < dna.length; i++) {
			char[] ch = dna[i].toCharArray();
	  		
			if (this.hrValid(ch))
	        	countRow += 1;
		}
		
		if(countRow > 1)
			return true;
		
		System.out.println("");
		countColumn = this.validateColumn(arrDna, sizeArr, 0);
		result = countRow + countColumn;
		if(result > 1) {
			return true;
		}
		
		countObl = this.oblValid(arrDna, sizeArr);
		
		result = result + countObl;
		if(result > 1)
			return true;
		
		return false;
	}
	
	
	public char[] createArray(String[] dna) {
		
		int sizeArr = dna.length;
		char[] arrDna = new char[sizeArr*sizeArr];
		int aux = 0;
		for(int i = 0; i < dna.length; i++ ) {
			char[] ch = dna[i].toCharArray();
			  		
			for(int j = 0; j < dna.length; j++) {
				int posicion = j + aux;
				arrDna[j + aux] = ch[j];
			}
			aux = aux + dna.length;
		}
		return arrDna;
	}
	
	public boolean validations(char[] dna) {
		for(int i = 0; i < dna.length; i++) {
			if((dna[i] != 'A') && (dna[i] != 'T') && (dna[i] != 'C') && (dna[i] != 'G')) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validationStr(String[] dna) {
		int sizeValidation = dna.length;
		
		//Validacion si es cuadrada
		for(int i = 0; i < dna.length; i++) {
			if(dna[i].length() != sizeValidation) {
				return false;
			}
		}
		
		//Validacion tamaño del arreglo
		if(sizeValidation < 4) {
			return false;
		}
		return true;
	}
	
	public boolean hrValid(char[] ch) {
		int valueValid = 3;
		boolean valid = false;
		int count = 0;
		for(int i = 0; i < ch.length - 1; i++) {
			if(ch[i] == ch[i + 1]) {
				int index = i + 1;
				count += 1; 
			}
			else {
				count = 0;
			}
			if(count == valueValid) {
				return true;
			}
		}
		return valid;
	}
	
	    
	public int validateColumn(char[] dna, int size, int init) {
		char[] arr = new char[size];
		int index = 0;
		int goal = size - 3;
		
		//Encuentro la columna
		for(int j = 0; j < size; j++) {
			arr[j] = dna[index + init];
			index = index + size;
		}
		
		this.setCountFor(countFor + 1);
		int validCount = 0;	
	
		for(int n = 0; n < arr.length - 1; n++) {
			if(arr[n] == arr[n + 1]) {
				validCount = validCount + 1;
			}
			else {
				validCount = 0;
			}
			if(validCount >= goal) {
				this.setValidColumns(this.getValidColumns() + 1);
				break;
			}
		}
		
	    if(this.getCountFor() < size ) {
				this.validateColumn(dna, size, this.getCountFor());
		}
		
		return this.getValidColumns();
	}
	
	public int oblValid(char[] ch, int size) {
		
		int validValue = 3;
		int validObl = 0;
		
		for(int i = 0; i < ch.length - size; i++) {
			int count = 0;
			for(int j = i; j < ch.length - size - 1; j = j + size + 1) {
				if(ch[i] == ch[j + size + 1]) {
					int index = j +size + 1;
					count = count + 1;
				}
				else {
					count = 0;
					break;
				}
				if(count >= validValue) {
					validObl = validObl + 1;
				}
			}
		}
		return validObl;
	}
	
	public int getCountFor() {
		return countFor;
	}

	public void setCountFor(int countFor) {
		this.countFor = countFor;
	}

	public int getValidColumns() {
		return validColumns;
	}

	public void setValidColumns(int validColumns) {
		this.validColumns = validColumns;
	}

}
