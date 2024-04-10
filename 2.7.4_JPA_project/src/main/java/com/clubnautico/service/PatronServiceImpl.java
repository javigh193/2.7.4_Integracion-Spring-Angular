package com.clubnautico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.clubnautico.converter.PatronConverter;
import com.clubnautico.converter.SocioConverter;
import com.clubnautico.dto.PatronDTO;
import com.clubnautico.entity.Patron;
import com.clubnautico.entity.Patron;
import com.clubnautico.repository.BaseRepository;
import com.clubnautico.repository.PatronRepository;

@Service
public class PatronServiceImpl implements PatronService{
	
	@Autowired
	private PatronRepository patronRepository;
	
	@Autowired
	private PatronConverter patronConverter;

	@Override
	public PatronDTO save(PatronDTO dto) throws Exception {
		try {
			Patron patron = patronRepository.save(patronConverter.dtoToEntity(dto));
			return patronConverter.entityToDTO(patron);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<PatronDTO> findAll() throws Exception {
		try {
			List<Patron> patrones = patronRepository.findAll();
			List<PatronDTO> patronesDTO = new ArrayList<>();
			for (Patron patron : patrones) {
				PatronDTO dto = patronConverter.entityToDTO(patron);
				patronesDTO.add(dto);
			}
			return patronesDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public PatronDTO findById(Long id) throws Exception {
		Optional<Patron> optPatron = patronRepository.findById(id);
		if (optPatron.isPresent()) {
			return patronConverter.entityToDTO(optPatron.get());
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public PatronDTO update(Long id, PatronDTO dto) throws Exception {
		Optional<Patron> optPatron = patronRepository.findById(id);
		if (optPatron.isPresent()) {
			Patron patron = optPatron.get();
			patron.setNombre(dto.getNombre());
			patron.setApellido(dto.getApellido());
			patron.setDni(dto.getDni());
			patron.setNumeroLicencia(dto.getNumeroLicencia());
			patronRepository.save(patron);
			return patronConverter.entityToDTO(patron);
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		patronRepository.deleteById(id);
	}
	
}
