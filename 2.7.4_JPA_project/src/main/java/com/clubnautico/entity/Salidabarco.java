package com.clubnautico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="salida_barco")
@Entity
public class Salidabarco extends Base{
	
	@ManyToOne
	@JoinColumn(name="fk_barco")
	private Barco barco;
	

	@ManyToOne
	@JoinColumn(name="fk_patron")
	private Patron patron;
	
	@Size(min=5, max=100)
	@Column(nullable=false)
	private String destino;
	
	@Future
	@Column(nullable=false)
	private java.time.LocalDateTime llegada;
	
	
}
