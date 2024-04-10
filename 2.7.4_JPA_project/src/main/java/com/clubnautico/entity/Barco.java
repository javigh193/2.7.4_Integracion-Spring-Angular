package com.clubnautico.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="barco")
@Entity
public class Barco extends Base{
	
	@Positive
	@Max(999999)
	@Column(nullable=false, unique=true)
    private Integer matricula;
	
	@Size(min=2, max=100)
	@Column(nullable=false)
    private String nombre;
	
	@OneToOne
	@JoinColumn(name="fk_amarre")
	private Amarre amarre;
	
	@ManyToOne
	@JoinColumn(name="fk_propietario", nullable=false)
	private Socio propietario;
	
	@JsonIgnore
	@OneToMany(mappedBy="barco", cascade=CascadeType.ALL, orphanRemoval=true)
	List<Salidabarco> salidasBarco;
  
}
