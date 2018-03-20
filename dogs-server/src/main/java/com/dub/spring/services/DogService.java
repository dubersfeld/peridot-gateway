package com.dub.spring.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.model.Dog;

@PreAuthorize("hasRole('DOG_USER')")
public interface DogService {

	public List<Dog> allDogs();
	
	public Dog getDog(long id);

	@PreAuthorize("hasRole('CREATE')")
	public void createDog(Dog dog);
	
	@PreAuthorize("hasRole('UPDATE')")
	public void updateDog(Dog dog);
	
	@PreAuthorize("hasRole('DELETE')")
	public void deleteDog(long id);
			


}
