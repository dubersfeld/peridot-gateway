package com.dub.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dub.spring.exception.DogAccessDeniedException;
import com.dub.spring.exception.DogNotFoundException;
import com.dub.spring.model.Dog;
import com.dub.spring.services.DogService;

@Controller
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	@RequestMapping("/allDogs")
	public ModelAndView allDogs() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		
		List<Dog> dogs = dogService.allDogs();
		
		for (Dog dog : dogs) {
			System.out.println(dog);
		}
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("dogs", dogs);
			
		return new ModelAndView("dogs/allDogs", params);
	}

	@RequestMapping(value = "/createDog")
	public String createDog(
			@Valid @ModelAttribute("dogForm") DogForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "dogs/createDog";
		} else {
			Dog dog = new Dog().withName(form.getName())
						.withBreed(form.getBreed())
						.withAge(form.getAge());
			
			
			try {
				dogService.createDog(dog);	
				return "dogs/createDogSuccess";
			} catch (AccessDeniedException e) {
				return "accessDenied";
			}
		}
	}


	@RequestMapping(value = "/updateDog")
	public ModelAndView updateDog(ModelMap model) {
		
		model.addAttribute("getDog", new DogIdForm());
		
		return new ModelAndView("dogs/updateDog1", model);
	}
	
	@RequestMapping(value = "/updateDog1")
	public String updateDog(
				@Valid @ModelAttribute("getDog") DogIdForm form,
				BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
	
			return "dogs/updateDog1";
		} else {
			try {
				long dogId = form.getId();
					
				Dog dog = dogService.getDog(dogId);
				
				model.addAttribute("dog", dog);
				return "dogs/updateDog2";
			} catch (DogNotFoundException e) {
				result.rejectValue("id", "notFound", "dog not found");							
				return "dogs/updateDog1";
			} catch (AccessDeniedException e) {
				return "accessDenied";
			} catch (RuntimeException e) {
				return "error";
			}// try
		}// if
		
	}
	
	@RequestMapping(value = "/updateDog2", 
							method = RequestMethod.POST)
	public String updateDog2(
						@Valid @ModelAttribute("dog") DogUpdateForm form,
						BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "dogs/updateDog2";			
		} else {
			try {
				Dog dog = new Dog()
						.withId(form.getId())
						.withName(form.getName())
						.withBreed(form.getBreed())
						.withAge(form.getAge());
				
				dogService.updateDog(dog);			
				return "dogs/updateDogSuccess";
			} catch (DogAccessDeniedException e) {
				return "dogs/accessDenied";
			} catch (AccessDeniedException e) {
				return "accessDenied";
			} catch (RuntimeException e) {
				return "error";
			}				
		}// if		
	}
	
	@RequestMapping(value = "/deleteDog", method = RequestMethod.GET)
	public ModelAndView deleteDog(ModelMap model) {
				
		model.addAttribute("getDog", new DogIdForm());
		
		return new ModelAndView("dogs/deleteDog", model);
	}
	
	
	@RequestMapping(value = "/deleteDog", method = RequestMethod.POST)
	public String deleteDog(
				@Valid @ModelAttribute("getDog") DogIdForm form,
				BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "dogs/deleteDog";
		} else {
			try {
				
				long dogId = form.getId();
				Dog dog = dogService.getDog(dogId);
				model.addAttribute("dog", dog);
				dogService.deleteDog(dogId);
				return "dogs/deleteDogSuccess";
			} catch (DogNotFoundException e) {
				result.rejectValue("id", "notFound", "dog not found");							
				return "dogs/deleteDog";
			} catch (AccessDeniedException e) {
				return "accessDenied";
			} catch (RuntimeException e) {
				return "error";
			}
		}
	}	
}
