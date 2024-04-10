package com.clubnautico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.clubnautico.converter.AmarreConverter;
import com.clubnautico.dto.AmarreDTO;
import com.clubnautico.entity.Amarre;
import com.clubnautico.repository.AmarreRepository;
import com.clubnautico.repository.BaseRepository;

import jakarta.transaction.Transactional;

@Service
public class AmarreServiceImpl implements AmarreService {
	
	@Autowired
	private AmarreRepository amarreRepository;
	
	@Autowired
	private AmarreConverter amarreConverter;

	@Override
	@Transactional
	public List<AmarreDTO> findByCuotaGreaterThan(Double cuota) throws Exception {
		try {
			List<Amarre> amarres = amarreRepository.findByCuotaGreaterThan(cuota);
			List<AmarreDTO> amarresDTO = new ArrayList<>();
			for (Amarre amarre : amarres) {
				AmarreDTO dto = amarreConverter.entityToDTO(amarre);
				amarresDTO.add(dto);
			}
			return amarresDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public AmarreDTO save(AmarreDTO dto) throws Exception {
		try {
			Amarre amarre = amarreRepository.save(amarreConverter.dtoToEntity(dto));
			return amarreConverter.entityToDTO(amarre);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<AmarreDTO> findAll() throws Exception {
		try {
			List<Amarre> amarres = amarreRepository.findAll();
			List<AmarreDTO> amarresDTO = new ArrayList<>();
			for (Amarre amarre : amarres) {
				AmarreDTO dto = amarreConverter.entityToDTO(amarre);
				amarresDTO.add(dto);
			}
			return amarresDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public AmarreDTO findById(Long id) throws Exception {
		Optional<Amarre> optAmarre = amarreRepository.findById(id);
		if (optAmarre.isPresent()) {
			return amarreConverter.entityToDTO(optAmarre.get());
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public AmarreDTO update(Long id, AmarreDTO dto) throws Exception {
		Optional<Amarre> optAmarre = amarreRepository.findById(id);
		if (optAmarre.isPresent()) {
			Amarre amarre = optAmarre.get();
			amarre.setNumero(dto.getNumero());
			amarre.setCuota(dto.getCuota());
			amarreRepository.save(amarre);
			return amarreConverter.entityToDTO(amarre);
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		amarreRepository.deleteById(id);
	}
	
}