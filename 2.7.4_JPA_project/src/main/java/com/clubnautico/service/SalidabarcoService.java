package com.clubnautico.service;

import java.util.List;

import com.clubnautico.dto.SalidabarcoDTO;

public interface SalidabarcoService {
	
	SalidabarcoDTO save(SalidabarcoDTO dto) throws Exception;
	List<SalidabarcoDTO> findAll() throws Exception;
	SalidabarcoDTO findById(Long id) throws Exception;	
	SalidabarcoDTO update(Long id, SalidabarcoDTO dto) throws Exception;
	void delete(Long id) throws Exception;
	
	List<SalidabarcoDTO> search(String filtro) throws Exception;  
	
}
