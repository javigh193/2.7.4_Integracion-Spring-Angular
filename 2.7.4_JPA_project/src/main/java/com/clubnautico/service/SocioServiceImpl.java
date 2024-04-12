package com.clubnautico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.clubnautico.converter.SocioConverter;
import com.clubnautico.dto.SocioDTO;
import com.clubnautico.entity.Socio;
import com.clubnautico.repository.SocioRepository;

@Service
public class SocioServiceImpl implements SocioService{

	@Autowired
	private SocioRepository socioRepository;
	
	@Autowired
	private SocioConverter socioConverter;

	@Override
	public SocioDTO save(SocioDTO dto) throws Exception {
		try {
			Socio socio = socioRepository.save(socioConverter.dtoToEntity(dto));
			return socioConverter.entityToDTO(socio);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<SocioDTO> findAll() throws Exception {
		try {
			List<Socio> socios = socioRepository.findAll();
			List<SocioDTO> sociosDTO = new ArrayList<>();
			for (Socio socio : socios) {
				SocioDTO dto = socioConverter.entityToDTO(socio);
				sociosDTO.add(dto);
			}
			return sociosDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public SocioDTO findById(Long id) throws Exception {
		Optional<Socio> optSocio = socioRepository.findById(id);
		if (optSocio.isPresent()) {
			return socioConverter.entityToDTO(optSocio.get());
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public SocioDTO update(Long id, SocioDTO dto) throws Exception {
		Optional<Socio> optSocio = socioRepository.findById(id);
		if (optSocio.isPresent()) {
			Socio socio = optSocio.get();
			socio.setNombre(dto.getNombre());
			socio.setApellido(dto.getApellido());
			socio.setDni(dto.getDni());
			socio.setNumeroSocio(dto.getNumeroSocio());
			socioRepository.save(socio);
			return socioConverter.entityToDTO(socio);
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		socioRepository.deleteById(id);
	}
	
}
