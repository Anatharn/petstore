package sfeir.sds.petstore.springservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PetRepository extends CrudRepository<Pet, Integer>  {

	List<Pet> findByStatus(@Param("status") String status);
	List<Pet> findByCategory(@Param("category") String category );
}
