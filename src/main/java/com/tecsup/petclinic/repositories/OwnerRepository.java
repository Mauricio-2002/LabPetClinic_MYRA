package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;

@Repository
public interface OwnerRepository 
	extends CrudRepository<Owner, Long>{
	// Busqueda por primer nombre
	List<Owner> findByFirstName(String firstName);
	
	//Busqueda por segundo nombre
	List<Owner> findByLastName(String lastName);
	
	//Busqueda por telefono
	List<Owner> findByTelephone(String telephone);
}
