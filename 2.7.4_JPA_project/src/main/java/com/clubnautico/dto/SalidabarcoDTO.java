package com.clubnautico.dto;

import com.clubnautico.entity.Patron;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SalidabarcoDTO {
	
	private Long id;
	
	@NotNull(message="Se debe indicar el barco.")
	private Long barcoId;
	
	@NotNull(message="Se debe indicar el patrón.")
	private Long patronId;
	
	@NotNull(message="Se debe indicar el destino.")
	@Size(
		min=5,
		max=100,
		message="El nombre del lugar de destino debe contener un mínimo de 5 caracteres y un máximo de 100"
	)
	private String destino;
	
	@NotNull(message="Se debe indicar la fecha de llegada estimada.")
	@Future(message="La fecha de llegada debe ser futura.")
	private java.time.LocalDateTime llegada;
	
}
