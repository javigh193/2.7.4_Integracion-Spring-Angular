package com.clubnautico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.clubnautico.converter.SalidabarcoConverter;
import com.clubnautico.dto.BarcoDTO;
import com.clubnautico.dto.SalidabarcoDTO;
import com.clubnautico.entity.Amarre;
import com.clubnautico.entity.Barco;
import com.clubnautico.entity.Patron;
import com.clubnautico.entity.Salidabarco;
import com.clubnautico.entity.Socio;
import com.clubnautico.repository.BarcoRepository;
import com.clubnautico.repository.BaseRepository;
import com.clubnautico.repository.PatronRepository;
import com.clubnautico.repository.SalidabarcoRepository;

import jakarta.transaction.Transactional;

@Service
public class SalidabarcoServiceImpl implements SalidabarcoService {
	
	@Autowired
	private SalidabarcoRepository salidabarcoRepository;
	
	@Autowired
	private BarcoRepository barcoRepository;
	
	@Autowired
	private PatronRepository patronRepository;
	
	@Autowired
	private SalidabarcoConverter salidabarcoConverter;

	@Override
	public SalidabarcoDTO save(SalidabarcoDTO dto) throws Exception {
		Optional<Patron> optPatron = patronRepository.findById(dto.getPatronId());
		Optional<Barco> optBarco = barcoRepository.findById(dto.getBarcoId());
		if (optPatron.isPresent() && optBarco.isPresent()) {
			Salidabarco salidabarco = salidabarcoRepository.save(salidabarcoConverter.dtoToEntity(dto));
			salidabarco.setPatron(optPatron.get());
			salidabarco.setBarco(optBarco.get());
			salidabarco = salidabarcoRepository.save(salidabarco);
			return salidabarcoConverter.entityToDTO(salidabarco);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public List<SalidabarcoDTO> findAll() throws Exception {
		try {
			List<Salidabarco> salidasbarco = salidabarcoRepository.findAll();
			List<SalidabarcoDTO> salidasbarcoDTO = new ArrayList<>();
			for (Salidabarco salidabarco : salidasbarco) {
				SalidabarcoDTO dto = salidabarcoConverter.entityToDTO(salidabarco);
				salidasbarcoDTO.add(dto);
			}
			return salidasbarcoDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public SalidabarcoDTO findById(Long id) throws Exception {
		Optional<Salidabarco> optSalidabarco = salidabarcoRepository.findById(id);
		if (optSalidabarco.isPresent()) {
			return salidabarcoConverter.entityToDTO(optSalidabarco.get());
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public SalidabarcoDTO update(Long id, SalidabarcoDTO dto) throws Exception {
		Optional<Salidabarco> optSalidabarco = salidabarcoRepository.findById(id);
		if (optSalidabarco.isPresent()) {
			Optional<Patron> optPatron = patronRepository.findById(dto.getPatronId());
			Optional<Barco> optBarco = barcoRepository.findById(dto.getBarcoId());
			if (optPatron.isPresent() && optBarco.isPresent()) {
				Salidabarco salidabarco = optSalidabarco.get();
				salidabarco.setDestino(dto.getDestino());
				salidabarco.setLlegada(dto.getLlegada());
				salidabarco.setPatron(optPatron.get());
				salidabarco.setBarco(optBarco.get());
				salidabarcoRepository.save(salidabarco);
				return salidabarcoConverter.entityToDTO(salidabarco);
			} else {
			throw new NotFoundException();	
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		salidabarcoRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public List<SalidabarcoDTO> search(String filtro) throws Exception {
		try {
			List<Salidabarco> salidas = salidabarcoRepository.search(filtro);
			List<SalidabarcoDTO> salidasDTO = new ArrayList<>();
			for (Salidabarco salida : salidas) {
				SalidabarcoDTO dto = salidabarcoConverter.entityToDTO(salida);
				salidasDTO.add(dto);
			}
			return salidasDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
