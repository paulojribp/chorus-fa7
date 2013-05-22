package com.chorus.service;

import java.util.List;

import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;
import com.chorus.exceptions.UsuarioInexistenteException;

public interface UsuarioService {

	String salvar(UsuarioDto usuario) throws Exception;
	
	UsuarioDto findByUsuario(Usuario usuario);
	
	void seguir(Long userId, Long userASeguirId) throws ErroAoSeguirException;

	void seguir(Long id, String username) throws ErroAoSeguirException;
	
	void refresh(Usuario user);

	Usuario login(Usuario usuario) throws UsuarioInexistenteException;

	List<UsuarioDto> findSeguindo(Usuario user);

	List<UsuarioDto> findSeguidores(Usuario user);

	void deixarSeguir(Long id, String username);

}
