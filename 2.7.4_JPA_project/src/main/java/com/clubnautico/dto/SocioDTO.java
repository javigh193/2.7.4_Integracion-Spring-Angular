package com.clubnautico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SocioDTO {

	private Long id;
	@NotNull(message="Se debe indicar el nombre.")
	@Size(
			min=2,
			max=30,
			message="El nombre debe tener un tamaño mínimo de 2 caracteres y máximo de 30."
		)
	private String nombre;
	
	@NotNull(message="Se debe indicar el apellido.")
	@Size(
			min=2,
			max=30,
			message="El nombre debe tener un tamaño mínimo de 2 caracteres y máximo de 30."
	)
	private String apellido;
	
	@NotNull(message="Se debe indicar el dni.")
	@Size(
			min=7,
			max=7,
			message="EL dni debe estar formado por 6 dígitos seguidos de una letra mayúscula."
	)
	@Pattern(
			regexp="^\\d{6}[A-Z]$",
			message="EL dni debe estar formado por 6 dígitos seguidos de una letra mayúscula."
	)
	private String dni;
	
	@NotNull(message="Se debe indicar el número de socio.")
	@Positive(message="El número de socio debe ser un entero positivo.")
	@Max(value=999999, message="El número de socio debe ser menor que 999999")
	private Integer numeroSocio;
	
}
