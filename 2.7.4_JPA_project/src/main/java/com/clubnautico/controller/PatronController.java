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
import org.springframework.web.bind.annotation.RestController;

import com.clubnautico.dto.PatronDTO;
import com.clubnautico.service.PatronService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/patrones")
public class PatronController {

	@Autowired
	private PatronService patronService;
	
	private String errorgenerico = "{\"error\":\"Error. Por favor, intente mas tarde.\"}";
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			List<PatronDTO> patronDTO = patronService.findAll();
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(patronDTO);
					
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
					.body(patronService.findById(id));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(errorgenerico);
		}		
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody PatronDTO dto) {
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(patronService.save(dto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PatronDTO dto) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(patronService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			patronService.delete(id);
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(null);
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorgenerico);
		}		
	}
	
}
