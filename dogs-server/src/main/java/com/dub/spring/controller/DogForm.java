package com.dub.spring.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DogForm {

	@NotNull(message = "{validate.name.required}")
	@Size(min = 1, message = "{validate.name.required}")
	String name;
	
	@NotNull(message = "{validate.breed.required}")
	@Size(min = 1, message = "{validate.breed.required}")
	String breed;
	
	@NotNull(message = "{validate.age.required}")
	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	
}