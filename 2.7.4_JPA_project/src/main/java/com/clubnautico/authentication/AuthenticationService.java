package com.clubnautico.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clubnautico.entity.Role;
import com.clubnautico.entity.Usuario;
import com.clubnautico.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
	
	private final UsuarioRepository usuarioRepository;
	private final JwtService jwtService; 
	private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Usuario usuario=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(usuario);
        return AuthenticationResponse.builder()
            .token(token)
            .username(usuario.getUsername())
            .build();
	}
	
	public AuthenticationResponse register(RegisterRequest request) {
		Usuario usuario = new Usuario();
		usuario.setUsername(request.getUsername());
		usuario.setPassword(passwordEncoder.encode( request.getPassword()));
		usuario.setDni(request.getDni());
		usuario.setNombre(request.getNombre());
		usuario.setApellido(request.getApellido());
		usuario.setRole(Role.USER);
		
		usuarioRepository.save(usuario);
		
		return AuthenticationResponse.builder()
				.token(jwtService.getToken(usuario))
				.username(usuario.getUsername())
				.build();
	}
	
	
}
