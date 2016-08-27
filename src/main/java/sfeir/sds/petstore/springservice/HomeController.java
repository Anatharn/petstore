package sfeir.sds.petstore.springservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@Autowired
	PetRepository petRepository;

	@RequestMapping("/home")
	public String home() {
		return "index";
	}
	@RequestMapping(value = "/pets/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public @ResponseBody List<Pet> deletePetById(@PathVariable int id) {
		this.petRepository.delete( id );
		return this.petRepository.findByStatus( "available" );
	}
	
}
