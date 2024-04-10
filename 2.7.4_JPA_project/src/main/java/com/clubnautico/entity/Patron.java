package com.clubnautico.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="patron")
@Entity
public class Patron extends Persona{

	@Positive
	@Max(999999)
	@Column(name="numero_licencia", nullable=false, unique=true)
	private Integer numeroLicencia;
	
	@JsonIgnore
	@OneToMany(mappedBy="patron")
	List<Salidabarco> salidasBarco;
	
}
