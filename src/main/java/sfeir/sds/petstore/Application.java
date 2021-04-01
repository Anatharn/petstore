package sfeir.sds.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import sfeir.sds.petstore.entities.Category;
import sfeir.sds.petstore.entities.Pet;

/**
 * Ajout de commentaire pour tester
 * Et un nouveau commentaire
 */
@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@PropertySource("application.properties")
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);
        restConfiguration.exposeIdsFor( Pet.class );
        restConfiguration.exposeIdsFor( Category.class );
	}
 
}