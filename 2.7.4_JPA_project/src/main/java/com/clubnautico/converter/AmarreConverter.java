package com.clubnautico.converter;

import org.springframework.stereotype.Component;

import com.clubnautico.dto.AmarreDTO;
import com.clubnautico.entity.Amarre;

@Component
public class AmarreConverter {
	
	public Amarre dtoToEntity(AmarreDTO amarreDTO) {
		Amarre amarre = new Amarre();
		amarre.setNumero(amarreDTO.getNumero());
		amarre.setCuota(amarreDTO.getCuota());
		
		return amarre;
	}
	
	public AmarreDTO entityToDTO(Amarre amarre) {
		AmarreDTO amarreDTO = new AmarreDTO();
		amarreDTO.setId(amarre.getId());
		amarreDTO.setNumero(amarre.getNumero());
		amarreDTO.setCuota(amarre.getCuota());
		
		return amarreDTO;
	}

}
