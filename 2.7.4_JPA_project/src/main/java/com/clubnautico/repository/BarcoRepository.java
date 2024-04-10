package com.clubnautico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubnautico.entity.Barco;

//@Repository, JPA Repository lo resuelve con proxies de manera dinámica
public interface BarcoRepository extends BaseRepository<Barco, Long>{

	@Query(value="SELECT b FROM Barco b WHERE b.nombre LIKE %:filtro%")
	List<Barco> search(@Param("filtro") String filtro);
	
	List<Barco> findAllByPropietarioId(@Param("id_propietario") Long id_propietario);
	
}
