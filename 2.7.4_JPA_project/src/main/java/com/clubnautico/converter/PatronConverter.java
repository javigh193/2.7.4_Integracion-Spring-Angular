package com.clubnautico.converter;

import org.springframework.stereotype.Component;

import com.clubnautico.dto.PatronDTO;
import com.clubnautico.entity.Patron;

@Component
public class PatronConverter {

	public Patron dtoToEntity(PatronDTO patronDTO) {
		Patron patron = new Patron();
		patron.setNombre(patronDTO.getNombre());
		patron.setApellido(patronDTO.getApellido());
		patron.setDni(patronDTO.getDni());
		patron.setNumeroLicencia(patronDTO.getNumeroLicencia());
		
		return patron;
	}
	
	public PatronDTO entityToDTO(Patron patron) {
		PatronDTO patronDTO = new PatronDTO();
		patronDTO.setId(patron.getId());
		patronDTO.setNombre(patron.getNombre());
		patronDTO.setApellido(patron.getApellido());
		patronDTO.setDni(patron.getDni());
		patronDTO.setNumeroLicencia(patron.getNumeroLicencia());
		
		return patronDTO;
	}
	
}