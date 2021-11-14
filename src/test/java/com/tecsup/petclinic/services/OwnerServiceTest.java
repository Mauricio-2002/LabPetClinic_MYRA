package com.tecsup.petclinic.services;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "George";
		Owner owner = null;
		
		try {
			
			owner = ownerService.findById(ID);
			
		} catch (OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		}
		logger.info("" + owner);

		assertThat(owner.getFirstName(), is(NAME));
	}
	@Test
	public void testCreateOwner() {
		String OWNER_FIRSTNAME = "Lucas";
		String OWNER_LASTNAME = "Flores";
		String OWNER_ADDRESS = "Calle Paramonga 291";
		String OWNER_CITY = "Lima";
		String OWNER_TELEPHONE = "967915476";
		
		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		
		Owner OwnerCreated = ownerService.create(owner);
		
		logger.info("OWNER CREATED :" + OwnerCreated);
		
		//          ACTUAL                      , EXPECTED 
		assertThat(OwnerCreated.getId()         , notNullValue());
		assertThat(OwnerCreated.getFirstName()  , is(OWNER_FIRSTNAME));
		assertThat(OwnerCreated.getLastName()   , is(OWNER_LASTNAME));
		assertThat(OwnerCreated.getAddress()    , is(OWNER_ADDRESS));
		assertThat(OwnerCreated.getCity()       , is(OWNER_CITY));
		assertThat(OwnerCreated.getTelephone()  , is(OWNER_TELEPHONE));
	}
}
