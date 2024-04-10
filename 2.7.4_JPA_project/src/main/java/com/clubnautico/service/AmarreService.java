package com.clubnautico.service;

import java.util.List;

import com.clubnautico.dto.AmarreDTO;

public interface AmarreService {
	
	AmarreDTO save(AmarreDTO amarreDTO) throws Exception;
	List<AmarreDTO> findAll() throws Exception;
	AmarreDTO findById(Long id) throws Exception;	
	AmarreDTO update(Long id, AmarreDTO dto) throws Exception;
	void delete(Long id) throws Exception;
	
	List<AmarreDTO> findByCuotaGreaterThan(Double cuota) throws Exception;
	
}
