package com.dub.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dub.spring.exception.DogNotFoundException;
import com.dub.spring.exception.DogServerException;
import com.dub.spring.model.Dog;
import com.dub.spring.repositories.DogRepository;

@Service
public class DogServiceImpl implements DogService {

	@Autowired
	private DogRepository dogRepository;
	
	@Override
	public List<Dog> allDogs() {
		
		List<Dog> list = dogRepository.findAll();
		return list;
	}

	@Override
	public Dog getDog(long id) {
		
		Dog dog = dogRepository.findOne(id);
		
		// never return null
		if (dog != null) {
			return dog;
		} else {
			throw new DogNotFoundException();
		}
	}
	
		
	@Override
	public void createDog(Dog dog) {
			
		try {
			dogRepository.save(dog);
		} catch (Exception e) {
			throw new DogServerException();
		}
	}
	
	
	@Override
	public void updateDog(Dog dog) {
			
		dogRepository.save(dog);
	}

	@Override
	public void deleteDog(long id) {

		dogRepository.delete(id);
	}

}
