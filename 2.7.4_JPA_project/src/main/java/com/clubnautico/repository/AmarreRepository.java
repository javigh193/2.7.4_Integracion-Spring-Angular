package com.clubnautico.repository;

import java.util.List;

import com.clubnautico.entity.Amarre;

//@Repository, JPA Repository lo resuelve con proxies de manera dinámica
public interface AmarreRepository extends BaseRepository<Amarre, Long>{

	List<Amarre> findByCuotaGreaterThan(Double cuota);
	
}
