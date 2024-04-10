package com.clubnautico.converter;

import org.springframework.stereotype.Component;

import com.clubnautico.dto.SalidabarcoDTO;
import com.clubnautico.entity.Salidabarco;

@Component
public class SalidabarcoConverter {

	public Salidabarco dtoToEntity(SalidabarcoDTO salidabarcoDTO) {
		Salidabarco salidabarco = new Salidabarco();
		salidabarco.setDestino(salidabarcoDTO.getDestino());
		salidabarco.setLlegada(salidabarcoDTO.getLlegada());
		
		return salidabarco;
	}
	
	public SalidabarcoDTO entityToDTO(Salidabarco salidabarco) {
		SalidabarcoDTO salidabarcoDTO = new SalidabarcoDTO();
		salidabarcoDTO.setId(salidabarco.getId());
		salidabarcoDTO.setDestino(salidabarco.getDestino());
		salidabarcoDTO.setLlegada(salidabarco.getLlegada());
		salidabarcoDTO.setPatronId(salidabarco.getPatron().getId());
		salidabarcoDTO.setBarcoId(salidabarco.getBarco().getId());
		
		return salidabarcoDTO;
	}
}

