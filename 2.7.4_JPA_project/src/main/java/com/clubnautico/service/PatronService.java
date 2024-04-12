package com.clubnautico.service;

import java.util.List;

import com.clubnautico.dto.PatronDTO;

public interface PatronService {

	PatronDTO save(PatronDTO dto) throws Exception;
	List<PatronDTO> findAll() throws Exception;
	PatronDTO findById(Long id) throws Exception;	
	PatronDTO update(Long id, PatronDTO dto) throws Exception;
	void delete(Long id) throws Exception;
	
}
