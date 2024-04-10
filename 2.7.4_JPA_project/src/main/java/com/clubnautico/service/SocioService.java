package com.clubnautico.service;


import java.util.List;

import com.clubnautico.dto.SocioDTO;

public interface SocioService {
	
	SocioDTO save(SocioDTO dto) throws Exception;
	List<SocioDTO> findAll() throws Exception;
	SocioDTO findById(Long id) throws Exception;	
	SocioDTO update(Long id, SocioDTO dto) throws Exception;
	void delete(Long id) throws Exception;
}
