package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {
	
	private static final Logger Logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Override
	public Owner create(Owner owner) {
		// TODO Auto-generated method stub
		return ownerRepository.save(owner);
	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		// TODO Auto-generated method stub
		Owner owner = findById(id);
		ownerRepository.delete(owner);
	}

	@Override
	public Owner findById(long id) throws OwnerNotFoundException {
		// TODO Auto-generated method stub
		Optional<Owner> owner = ownerRepository.findById(id);
		
		if(!owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
		
		return owner.get();
	}

	@Override
	public List<Owner> findByFirstName(String firstname) {
		// TODO Auto-generated method stub
		List<Owner> owners = ownerRepository.findByFirstName(firstname);
		
		owners.stream().forEach(owner -> Logger.info(" " + firstname));
		
		return owners;
	}

	@Override
	public List<Owner> findByLastName(String lastname) {
		// TODO Auto-generated method stub
		List<Owner> owners = ownerRepository.findByLastName(lastname);
		
		owners.stream().forEach(owner -> Logger.info(" " + lastname));
		
		return owners;
	}

	@Override
	public List<Owner> findByTelephone(String telephone) {
		// TODO Auto-generated method stub
		List<Owner> owners = ownerRepository.findByTelephone(telephone);
		
		owners.stream().forEach(owner -> Logger.info(" " + telephone));
		
		return owners;
	}

	@Override
	public Iterable<Owner> findAll() {
		// TODO Auto-generated method stub
		return ownerRepository.findAll();
	}

}
