package sfeir.sds.petstore.entities;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( path = "categories" )
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	List<Category> findAll();
}
