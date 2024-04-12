package com.clubnautico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubnautico.entity.Salidabarco;

public interface SalidabarcoRepository extends BaseRepository<Salidabarco, Long> {

	@Query(
		value = "SELECT * FROM salida_barco AS s WHERE s.destino LIKE %?1%",
		nativeQuery = true
	)
	List<Salidabarco> search(String filtro);
	
}
