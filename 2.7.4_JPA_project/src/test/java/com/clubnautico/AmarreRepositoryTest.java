package com.clubnautico;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.clubnautico.entity.Amarre;
import com.clubnautico.repository.AmarreRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class AmarreRepositoryTest {
	
	@Autowired
	private AmarreRepository repository;
	
	@BeforeEach
	public void setUp() {
		Amarre amarre = new Amarre(1, 1050.00);
		repository.save(amarre);
	}
	
	@Test
	@Order(1)
	void testFindByCuotaGreaterThan() {
		Boolean actual1 = repository.findByCuotaGreaterThan(1000.0).isEmpty();
		Boolean actual2 = repository.findByCuotaGreaterThan(1500.0).isEmpty();
		assertFalse(actual1);
		assertTrue(actual2);
	}
	
}
