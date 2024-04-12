package com.clubnautico.repository;

import java.util.Optional;

import com.clubnautico.entity.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);
	
}
