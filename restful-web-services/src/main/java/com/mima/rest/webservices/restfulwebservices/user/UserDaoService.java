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
		//Saber el tamaño del arreglo
		int sizeArr = dna.length;
		//Crear arreglo de caracteres con el tamaño del arreglo de entrada
		char[] arrDna = new char[sizeArr*sizeArr];
		int countRow = 0;
		int countColumn = 0;
		int countObl = 0;
		int result = 0;
				
		//System.out.println("Validaciones str:::"+!this.validationStr(dna));
		if(!this.validationStr(dna))
			return false;
		
		arrDna = this.createArray(dna);
		System.out.println("Validacion:::"+!this.validations(arrDna));
		if(!this.validations(arrDna)) {
			System.out.println("Validacion CH:::"+!this.validations(arrDna));
			return false;
		}
			
		
		System.out.println("Tamaño del arreglo:::"+arrDna.length);
		for(int i = 0; i < arrDna.length; i++) {
			System.out.print(arrDna[i]);
		}
		System.out.println("");
		
		//Hacer validacion horizontal
		for(int i = 0; i < dna.length; i++) {
			//System.out.println("String antes de validar hr:::"+dna[i]);
			char[] ch = dna[i].toCharArray();
	  		
	        //Encontro cadena horizontal
			if (this.hrValid(ch))
	        	countRow += 1;
			
			System.out.println("Count Row:::"+countRow);
		}
		
		System.out.println("Contador horizontal:::"+countRow);
		if(countRow > 1)
			return true;
		
		System.out.println("");
		countColumn = this.validateColumn(arrDna, sizeArr, 0);
		System.out.println("Cantidad de columnas validas:::"+countColumn);
		result = countRow + countColumn;
		if(result > 1) {
			System.out.println("Suma entre filas y columnas:::"+result);
			return true;
		}
		
		countObl = this.oblValid(arrDna, sizeArr);
		
		result = result + countObl;
		System.out.println("result:::"+result);
		if(result > 1)
			return true;
		
		return false;
	}
	
	
	public char[] createArray(String[] dna) {
		//Saber el tamaño del arreglo
		int sizeArr = dna.length;
		//Crear arreglo de caracteres con el tamaño del arreglo de entrada
		char[] arrDna = new char[sizeArr*sizeArr];
		int aux = 0;
		//Convertir lista en array de caracteres
		for(int i = 0; i < dna.length; i++ ) {
			//char[] ch = new char[dna[i].length()];
			System.out.println("String:::"+dna[i]);
			//Convertir string en arreglo de caracteres
			char[] ch = dna[i].toCharArray();
			  		
			//Se debe ingresar los datos del arreglo en el arreglo mayor
			for(int j = 0; j < dna.length; j++) {
				//Se tiene un arreglo con los caracteres del string
				//ch[j] = dna[i].charAt(j);
				//System.out.println("Caracter:::"+ch[j]);
				//Se introducen los valores del arreglo de caracteres en el arreglo principal
				int posicion = j + aux;
				//System.out.println("Posicion donde se va a ingresar:::"+posicion);
				arrDna[j + aux] = ch[j];
			}
			aux = aux + dna.length;
		}
		return arrDna;
	}
	
	public boolean validations(char[] dna) {
		
		//Validacion caracteres
		for(int i = 0; i < dna.length; i++) {
			System.out.println("Caracter:::"+dna[i]);
			
			if((dna[i] != 'A') && (dna[i] != 'T') && (dna[i] != 'C') && (dna[i] != 'G')) {
				System.out.println("dna[i]:::"+dna[i]);
				return false;
			}
		}
		return true;
	}
	
	public boolean validationStr(String[] dna) {
		int sizeValidation = dna.length;
		System.out.println("size1:::"+sizeValidation);
		
		//Validacion si es cuadrada
		for(int i = 0; i < dna.length; i++) {
			System.out.println("for:::"+dna[i].length());
			System.out.println("sizevalidation:::"+sizeValidation);
			if(dna[i].length() != sizeValidation) {
				System.out.println("No es cuadrada1:::"+dna[i].length());
				return false;
			}
		}
		
		//Validacion tamaño del arreglo
		if(sizeValidation < 4) {
			System.out.println("El tamaño del arreglo es menor a 4");
			return false;
		}
		return true;
	}
	
	public boolean hrValid(char[] ch) {
		int valueValid = 3;
		boolean valid = false;
		int validRow = 0;
		int count = 0;
		for(int i = 0; i < ch.length - 1; i++) {
			if(ch[i] == ch[i + 1]) {
				int index = i + 1;
				System.out.println("Iguales:::"+ch[i] + " "+ i + " "+ch[i + 1]+ " "+ index);
				count += 1; 
			}
			else {
				count = 0;
			}
			if(count == valueValid) {
				System.out.println("Encontro una cadena");
				return true;
			}
		}
		return valid;
	}
	
	    
	public int validateColumn(char[] dna, int size, int init) {
		char[] arr = new char[size];
		int index = 0;
		int count = 0;
		int goal = size - 3;
		
		//Encuentro la columna
		for(int j = 0; j < size; j++) {
			System.out.println("Index:::"+index);
			arr[j] = dna[index + init];
			System.out.println("Dato a ingresar:::"+dna[index + init]);
			index = index + size;
			System.out.println("Dato ingresado:::"+arr[j]);
		}
		
		for(int i = 0; i < size; i++) {
			System.out.println("Verificación Dato en el arreglo:::"+arr[i]);
		}
		this.setCountFor(countFor + 1);
		System.out.println("Count :::"+count);
		//Ya tengo el
		int validCount = 0;	
	
		//Valida si si son iguales
		for(int n = 0; n < arr.length - 1; n++) {
			System.out.println("Dato en el arreglo suma:::"+arr[n]);
			
			if(arr[n] == arr[n + 1]) {
				System.out.println("Son iguales:::"+arr[n] + " " +arr[n + 1]);
				validCount = validCount + 1;
				System.out.println("Contador columna:::"+validCount);
			}
			else {
				validCount = 0;
			}
			if(validCount >= goal) {
				//Debe sumar a count el acierto y seguir con la siguiente columna
				System.out.println("Cantidad de aciertos por columna:::"+validCount);
				this.setValidColumns(this.getValidColumns() + 1);
				break;
			}
		}
		
	    if(this.getCountFor() < size ) {
				System.out.println("No ha terminado de recorrer las columnas:::"+this.getCountFor());
				this.validateColumn(dna, size, this.getCountFor());
		}
		
		System.out.println("Cantidad de columnas validas:::"+this.getValidColumns());
		return this.getValidColumns();
	}
	
	public int oblValid(char[] ch, int size) {
		/**
		 * Coger el cacter que esta en la primera posicion y compararlo con el
		 * que esta en la primera posicio  del siguiente string
		 * 
		 */
		
		int validValue = 3;
		int validObl = 0;
		
		
		for(int i = 0; i < ch.length - size; i++) {
			System.out.println("Indice interior:::"+i);
			int count = 0;
			for(int j = i; j < ch.length - size - 1; j = j + size + 1) {
				System.out.println("Indice i:::"+i);
				System.out.println("Indice j:::"+j);
				if(ch[i] == ch[j + size + 1]) {
					int index = j +size + 1;
					System.out.println("Son iguales:::"+ch[i]+" "+i+" "+ch[j + size + 1]+" "+index);
					count = count + 1;
					System.out.println("Count:::"+count);
				}
				else {
					count = 0;
					break;
				}
				if(count >= validValue) {
					System.out.println("Completo la cantidad de caracteres iguales:::"+count);
					validObl = validObl + 1;
					
					//count = 0;
					//result = result + 1;
					//break;
					//return true;
				}
			}//1 for
			//Debo pasar al segundo string
			//El segundo string comienza en size
		}
		
		System.out.println("validObl:::"+validObl);
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
