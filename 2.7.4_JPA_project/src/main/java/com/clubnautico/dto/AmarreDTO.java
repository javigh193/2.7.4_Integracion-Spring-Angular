package com.clubnautico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AmarreDTO {
	
	private Long id;
	
	@NotNull(message="Se debe indicar el número.")
	@Positive(message="El número de amarre debe ser un entero positivo.")
    private Integer numero;
	
	@NotNull(message="Se debe indicar la cuota.")
	@DecimalMin(value="1000.00", message="La cuota debe ser, como mínimo, 1000.00.")
    private Double cuota;
	
}
