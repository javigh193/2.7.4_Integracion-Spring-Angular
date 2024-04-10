package com.clubnautico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.clubnautico.converter.BarcoConverter;
import com.clubnautico.dto.BarcoDTO;
import com.clubnautico.entity.Amarre;
import com.clubnautico.entity.Barco;
import com.clubnautico.entity.Socio;
import com.clubnautico.repository.AmarreRepository;
import com.clubnautico.repository.BarcoRepository;
import com.clubnautico.repository.BaseRepository;
import com.clubnautico.repository.SocioRepository;

@Service
public class BarcoServiceImpl implements BarcoService {
	
	@Autowired
	private BarcoRepository barcoRepository;
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Autowired
	private AmarreRepository amarreRepository;

	@Autowired
	private BarcoConverter barcoConverter;
	
	@Override
	public BarcoDTO save(BarcoDTO dto) throws Exception {
		Optional<Socio> optSocio = socioRepository.findById(dto.getPropietarioId());
		Optional<Amarre> optAmarre = amarreRepository.findById(dto.getAmarreId());
		if (optSocio.isPresent() && optAmarre.isPresent()) {
			Barco barco = barcoRepository.save(barcoConverter.dtoToEntity(dto));
			barco.setPropietario(optSocio.get());
			barco.setAmarre(optAmarre.get());
			barco = barcoRepository.save(barco);
			return barcoConverter.entityToDTO(barco);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public List<BarcoDTO> findAll() throws Exception {
		try {
			List<Barco> barcos = barcoRepository.findAll();
			List<BarcoDTO> barcosDTO = new ArrayList<>();
			for (Barco barco : barcos) {
				BarcoDTO dto = barcoConverter.entityToDTO(barco);
				barcosDTO.add(dto);
			}
			return barcosDTO;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public BarcoDTO findById(Long id) throws Exception {
		Optional<Barco> optBarco = barcoRepository.findById(id);
		if (optBarco.isPresent()) {
			return barcoConverter.entityToDTO(optBarco.get());
		} else {
			throw new NotFoundException();	
		}
	}

	@Override
	public BarcoDTO update(Long id, BarcoDTO dto) throws Exception {
		Optional<Barco> optBarco = barcoRepository.findById(id);
		if (optBarco.isPresent()) {
			Optional<Socio> optSocio = socioRepository.findById(dto.getPropietarioId());
			Optional<Amarre> optAmarre = amarreRepository.findById(dto.getAmarreId());
			if (optSocio.isPresent() && optAmarre.isPresent()) {
				Barco barco = optBarco.get();
				barco.setNombre(dto.getNombre());
				barco.setMatricula(dto.getMatricula());
				barco.setPropietario(optSocio.get());
				barco.setAmarre(optAmarre.get());
				barcoRepository.save(barco);
				return barcoConverter.entityToDTO(barco);
			} else {
			throw new NotFoundException();	
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		barcoRepository.deleteById(id);
	}
	
	@Override
	public List<Barco> search(String filtro) throws Exception {
		try {
			List<Barco> barcos = barcoRepository.search(filtro);
			return barcos;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Barco> findAllByPropietarioId(Long fk_propietario) throws Exception {
		try {
			List<Barco> barcos = barcoRepository.findAllByPropietarioId(fk_propietario);
			return barcos;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
