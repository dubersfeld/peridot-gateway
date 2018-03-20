package com.dub.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dub.spring.model.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
