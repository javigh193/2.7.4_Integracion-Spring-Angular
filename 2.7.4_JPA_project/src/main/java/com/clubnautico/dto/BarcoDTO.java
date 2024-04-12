package com.clubnautico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class BarcoDTO {
	
	private Long id;
	@NotNull(message="Se debe indicar la matrícula.")
	@Positive(message="La matrícula debe ser un entero positivo.")
	@Max(value=999999, message="La mátricula debe ser un entero menor que 999999")
    private Integer matricula;
	
	@NotNull(message="Se debe indicar el nombre.")
	@Size(
		min=2, 
		max=100,
		message="El nombre del barco debe contener un mínimo de 2 caracteres y un máximo de 100"
	)
    private String nombre;
	
	@NotNull(message="Se debe indicar el amarre.")
	private Long amarreId;
	
	@NotNull(message="Se debe indicar el propietario.")
	private Long propietarioId;
	
}
