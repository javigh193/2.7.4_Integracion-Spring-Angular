package com.clubnautico.converter;

import org.springframework.stereotype.Component;

import com.clubnautico.dto.SocioDTO;
import com.clubnautico.entity.Socio;

@Component
public class SocioConverter {

	public Socio dtoToEntity(SocioDTO socioDTO) {
		Socio socio = new Socio();
		socio.setNombre(socioDTO.getNombre());
		socio.setApellido(socioDTO.getApellido());
		socio.setDni(socioDTO.getDni());
		socio.setNumeroSocio(socioDTO.getNumeroSocio());
		
		return socio;
	}
	
	public SocioDTO entityToDTO(Socio socio) {
		SocioDTO socioDTO = new SocioDTO();
		socioDTO.setId(socio.getId());
		socioDTO.setNombre(socio.getNombre());
		socioDTO.setApellido(socio.getApellido());
		socioDTO.setDni(socio.getDni());
		socioDTO.setNumeroSocio(socio.getNumeroSocio());
		
		return socioDTO;
	}
	
}
