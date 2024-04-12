package com.clubnautico.service;

import java.util.List;

import com.clubnautico.dto.BarcoDTO;
import com.clubnautico.entity.Barco;

public interface BarcoService {

	BarcoDTO save(BarcoDTO dto) throws Exception;
	List<BarcoDTO> findAll() throws Exception;
	BarcoDTO findById(Long id) throws Exception;	
	BarcoDTO update(Long id, BarcoDTO dto) throws Exception;
	void delete(Long id) throws Exception;
	
	List<Barco> search(String filtro) throws Exception;
	List<Barco> findAllByPropietarioId(Long fk_propietario) throws Exception;
	
}
