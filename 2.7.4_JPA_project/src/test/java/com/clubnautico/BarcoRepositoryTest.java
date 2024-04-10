package com.clubnautico;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.clubnautico.entity.Amarre;
import com.clubnautico.entity.Barco;
import com.clubnautico.entity.Socio;
import com.clubnautico.repository.AmarreRepository;
import com.clubnautico.repository.BarcoRepository;
import com.clubnautico.repository.SocioRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class BarcoRepositoryTest {
	
	@Autowired
	private BarcoRepository barcoRepository;
	
	@Autowired
	private AmarreRepository amarreRepository;
	
	@Autowired
	private SocioRepository socioRepository;
		
	@BeforeEach
	public void setUp() {
		Amarre amarre = new Amarre(1, 2000.00);
		amarre = amarreRepository.save(amarre);
		Socio socio = new Socio();
		socio.setNombre("Juán");
		socio.setApellido("López");
		socio.setDni("123456A");
		socio.setNumeroSocio(123456);
		socio = socioRepository.save(socio);
		Barco barco = new Barco();
		barco.setNombre("Bella Vista");
		barco.setMatricula(123456);
		barco.setAmarre(amarre);
		barco.setPropietario(socio);
		barco = barcoRepository.save(barco);
	}
	
//	@Test
//	@Order(1)
//	void testSave() {
//		Amarre amarre = new Amarre(1, 2000.00);
//		amarre = amarreRepository.save(amarre);
//		Socio socio = new Socio();
//		socio.setNombre("Juán");
//		socio.setApellido("López");
//		socio.setDni("123456A");
//		socio.setNumeroSocio(123456);
//		socio = socioRepository.save(socio);
//		Barco barco = new Barco();
//		barco.setNombre("Bella Vista");
//		barco.setMatricula(123456);
//		barco.setAmarre(amarre);
//		barco.setPropietario(socio);
//		Barco expected = barcoRepository.save(barco);
//		Barco actual = barcoRepository.findById(barco.getId()).get(); 
//		assertEquals(expected, actual); 
//	}
		
	@Test
	@Order(1)
	void testSearch() {
		Integer expected1 = 1;
		Integer expected2 = 0;
		Integer actual1 = barcoRepository.search("Bella Vista").size();
		Integer actual2 = barcoRepository.search("Maresía").size();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	@Order(2)
	void testFindAllByPropietarioId() {
		//Ojo con la secuencia de generación de Id en la DB
		List<Barco> obtenido1 = barcoRepository.findAllByPropietarioId((long) 2);
		List<Barco> obtenido2 = barcoRepository.findAllByPropietarioId((long) 1);
		assertFalse(obtenido1.isEmpty());
		assertTrue(obtenido2.isEmpty());
	}
	
	@Test
	@Order(3)
	void testDelete() {
		//Ojo con la secuencia de generación de Id en la DB
		Optional<Barco> optBarco = barcoRepository.findById((long) 3);
		Boolean actual1 = optBarco.isPresent();
		barcoRepository.deleteById((long) 3);
		optBarco = barcoRepository.findById((long) 3);
		Boolean actual2 = optBarco.isPresent();
		assertTrue(actual1);
		assertFalse(actual2);
	}
	
}
