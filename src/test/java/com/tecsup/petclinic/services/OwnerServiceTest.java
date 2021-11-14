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

	
	@Test
    public void testFindByFirstName() {
		
        String FIND_FIRSTNAME = "George";
        int SIZE_EXPECTED = 1;
        
        List<Owner> owner = ownerService.findByFirstName(FIND_FIRSTNAME);

        assertThat(owner.size(), is(SIZE_EXPECTED));
    }
	
	@Test
	public void testFindByLastName() {
		
		String FINDLAST_NAME = "Franklin";
		int SIZE_EXPECTED = 1;
		
		List<Owner> owner = ownerService.findByLastName(FINDLAST_NAME);
		
		assertThat(owner.size(), is(SIZE_EXPECTED));
	}
	
	@Test
	public void testFindTelephone() {
		String FINDTELEPHONE = "6085551023";
		int SIZE_EXPECTED = 1;
		
		List<Owner> owner = ownerService.findByTelephone(FINDTELEPHONE);
		
		assertThat(owner.size(), is(SIZE_EXPECTED));
	}

	
	@Test
	public void testUpdateOwner() {
		String OWNER_FIRSTNAME = "Mauricio";
		String OWNER_LASTNAME = "Del Villar";
		String OWNER_ADDRESS = "Calle Paramonga 291";
		String OWNER_CITY = "Lima";
		String OWNER_TELEPHONE = "967915476";
		long create_id = -1;
		
		String UP_OWNER_FIRSTNAME = "Kamila";
		String UP_OWNER_LASTNAME = "San Miguel";
		String UP_OWNER_ADDRESS = "Calle Cayalti 338";
		String UP_OWNER_CITY = "Lima";
		String UP_OWNER_TELEPHONE = "958714235";
		
		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		
		// Created record
		logger.info(">" + owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>" + ownerCreated);
		
		create_id = ownerCreated.getId();
		
		// Create record
		ownerCreated.setFirstName(UP_OWNER_FIRSTNAME);
		ownerCreated.setLastName(UP_OWNER_LASTNAME);
		ownerCreated.setAddress(UP_OWNER_ADDRESS);
		ownerCreated.setCity(UP_OWNER_CITY);
		ownerCreated.setTelephone(UP_OWNER_TELEPHONE);
		
		// Execute update
		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>>" + upgradeOwner);
		
		//      ACTUAL                        , EXPECTED 
		assertThat(create_id                  ,notNullValue());
		assertThat(upgradeOwner.getId()       , is(create_id));
		assertThat(upgradeOwner.getFirstName(), is(UP_OWNER_FIRSTNAME));
		assertThat(upgradeOwner.getLastName() , is(UP_OWNER_LASTNAME));
		assertThat(upgradeOwner.getAddress()  , is(UP_OWNER_ADDRESS));
		assertThat(upgradeOwner.getCity()     , is(UP_OWNER_CITY));
		assertThat(upgradeOwner.getTelephone(), is(UP_OWNER_TELEPHONE));
	}
	
	@Test
	public void testDeleteOwner() {
		
		String OWNER_FIRSTNAME = "Kamila";
		String OWNER_LASTNAME = "San Miguel";
		String OWNER_ADDRESS = "Calle Cayalti 338";
		String OWNER_CITY = "Lima";
		String OWNER_TELEPHONE = "958714235";
		
		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		
		owner = ownerService.create(owner);
		logger.info("" + owner);
		
		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		} 
		
		try {
			ownerService.findById(owner.getId());
			assertThat(true, is(false));
		} catch (OwnerNotFoundException e) {
			assertThat(true, is(true));
		} 
	}

}
