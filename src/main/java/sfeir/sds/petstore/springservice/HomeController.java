package sfeir.sds.petstore.springservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sfeir.sds.petstore.entities.Pet;
import sfeir.sds.petstore.entities.PetRepository;

@Controller
public class HomeController {
	
	@Autowired
	PetRepository petRepository;
	
	@RequestMapping( value={ "/login" } )
	public String login() {
		return "login";
	}
	@RequestMapping( value={ "/home" } )
	public String home() {
		return "index";
	}
	@RequestMapping("/partials/pet-list")
	public String petList() {
		return "partials/pet-list";
	}
	@RequestMapping( value= {"/partials/pet-add" })
	public String petAdd() {
		return "partials/pet-add";
	}
	@RequestMapping( value= {"/partials/pet-details" })
	public String petDetails() {
		return "partials/pet-details";
	}
	@RequestMapping(value = "/pets/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public @ResponseBody void deletePetById(@PathVariable int id) {
		this.petRepository.delete( id );
	}
	@RequestMapping(value = "/pets/{id}", method=RequestMethod.GET, headers={"Accept=application/json"}, produces={"application/json"})
	public @ResponseBody Pet findPetById(@PathVariable int id) {
		return this.petRepository.findOne( id );
	}
}
