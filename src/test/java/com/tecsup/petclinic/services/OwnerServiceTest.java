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
}
