package com.clubnautico.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Persona extends Base{
	
	@Size(min=2, max=30)
	private String nombre;
	
	@Size(min=2, max=30)
	private String apellido;
	
	@Size(min=7, max=7)
	@Column(nullable=false, unique=true)
	private String dni;
	
}
