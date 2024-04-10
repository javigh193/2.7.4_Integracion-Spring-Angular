package com.clubnautico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clubnautico.dto.AmarreDTO;
import com.clubnautico.service.AmarreService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/amarres")
public class AmarreController {
	
	@Autowired
	private AmarreService amarreService;
	
	private String errorgenerico = "{\"error\":\"Error. Por favor, intente mas tarde.\"}";
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			List<AmarreDTO> amarresDTO = amarreService.findAll();
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(amarresDTO);
					
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(errorgenerico);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(amarreService.findById(id));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(errorgenerico);
		}		
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody AmarreDTO dto) {
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(amarreService.save(dto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AmarreDTO dto) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(amarreService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			amarreService.delete(id);
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(null);
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findByCuotaGreaterThan(@RequestParam Double cuota) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(amarreService.findByCuotaGreaterThan(cuota));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("error");
		}
	}
}
