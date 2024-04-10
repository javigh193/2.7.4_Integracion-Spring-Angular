package com.clubnautico.converter;

import org.springframework.stereotype.Component;

import com.clubnautico.dto.BarcoDTO;
import com.clubnautico.entity.Barco;

@Component
public class BarcoConverter {

	public Barco dtoToEntity(BarcoDTO socioDTO) {
		Barco socio = new Barco();
		socio.setNombre(socioDTO.getNombre());
		socio.setMatricula(socioDTO.getMatricula());
		
		return socio;
	}
	
	public BarcoDTO entityToDTO(Barco barco) {
		BarcoDTO barcoDTO = new BarcoDTO();
		barcoDTO.setId(barco.getId());
		barcoDTO.setNombre(barco.getNombre());
		barcoDTO.setMatricula(barco.getMatricula());
		barcoDTO.setPropietarioId(barco.getPropietario().getId());
		barcoDTO.setAmarreId(barco.getAmarre().getId());
		
		return barcoDTO;
	}
	
}
