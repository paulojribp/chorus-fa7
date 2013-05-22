package com.chorus.service;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.auth.UserInfo;
import com.chorus.dao.UsuarioDao;
import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;
import com.chorus.exceptions.UsuarioConfirmacaoSenhaException;
import com.chorus.exceptions.UsuarioEmailInvalidoException;
import com.chorus.exceptions.UsuarioInexistenteException;
import com.chorus.exceptions.UsuarioJaExisteException;
import com.chorus.exceptions.UsuarioSenhaInvalidaException;
import com.chorus.exceptions.UsuarioUsernameInvalidoException;
import com.chorus.util.ProfilePictureFinder;

@Component
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDao dao;
	
	private ProfilePictureFinder pictureFinder;
	
	private UserInfo userInfo;
	
	public UsuarioServiceImpl(UsuarioDao usuarioDao, ProfilePictureFinder pictureFinder, UserInfo userInfo) {
		this.dao = usuarioDao;
		this.pictureFinder = pictureFinder;
		this.userInfo = userInfo;
	}

	private void validar(UsuarioDto usuario) throws Exception {
		Usuario userToSave = usuario.getUsuario();
		
		if (userToSave.getSenha() == null || userToSave.getSenha().length() < 6) {
			throw new UsuarioSenhaInvalidaException("Senha deve ser maior que 6 caracteres");
		}
		if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			throw new UsuarioConfirmacaoSenhaException("A confirmação de senha é inválida");
		}
		if (userToSave == null || !userToSave.isValido()) {
			throw new UsuarioUsernameInvalidoException("Usuário não é válido.");
		}
		if (userToSave.getUsername() == null) {
			throw new UsuarioUsernameInvalidoException("Username não pode ser nulo");
		}
		if (userToSave.getEmail() == null) {
			throw new UsuarioEmailInvalidoException("Email não pode ser nulo");
		}
		
		Usuario users = dao.findByUsuario(userToSave);
		if(users != null){
			throw new UsuarioJaExisteException("Usuario já existe");
		}	
	}

	@Override
	public String salvar(UsuarioDto usuario) throws Exception {
		validar(usuario);
		dao.create(usuario.getUsuario());
		return usuario.getUsername();
	}

	@Override
	public UsuarioDto findByUsuario(Usuario usuario) {
		Usuario u = dao.findByUsuario(usuario);
		UsuarioDto udto = new UsuarioDto(u);
		if (!userInfo.getUser().getUsername().equals(u.getUsername())) {
			if (dao.userAlreadyFollow(u.getId(), userInfo.getUser().getId()))
				udto.setSeguindo(true);
			if (dao.userAlreadyFollow(userInfo.getUser().getId(), u.getId()))
				udto.setSeguido(true);
		}
		
		return udto;
	}

	@Override
	public void seguir(Long userId, Long userASeguirId) throws ErroAoSeguirException {
		if (!dao.userAlreadyFollow(userId, userASeguirId)) {
			dao.seguir(userId, userASeguirId);
		}
	}

	@Override
	public void seguir(Long userId, String userASeguir) throws ErroAoSeguirException {
		Usuario u = dao.findByUsuario(new Usuario(userASeguir));
		if (!dao.userAlreadyFollow(userId, u.getId())) {
			dao.seguir(userId, u.getId());
		}
	}
	
	@Override
	public void deixarSeguir(Long id, String username) {
		Usuario u = dao.findByUsuario(new Usuario(username));
		if (dao.userAlreadyFollow(id, u.getId())) {
			dao.deixarSeguir(id, u.getId());
		}
	}
	
	@Override
	public void refresh(Usuario user) {
		dao.refresh(user);
	}

	@Override
	public Usuario login(Usuario usuario) throws UsuarioInexistenteException {
		String senha = usuario.getSenha();
		
		usuario = dao.findByUsuario(new Usuario(usuario.getUsername()));
		
		if (usuario == null || usuario.getId() == null || !usuario.getSenha().equals(senha))
			throw new UsuarioInexistenteException();
		
		return usuario;
	}

	@Override
	public List<UsuarioDto> findSeguindo(Usuario user) {
		List<Usuario> users = dao.findSeguindo(user);
		List<UsuarioDto> usersDto = new ArrayList<UsuarioDto>();
		
		if (users != null)
			for (Usuario u : users) {
				UsuarioDto udto = new UsuarioDto(u.getUsername(),u.getNome());
				udto.setSeguindo(true);
				udto.setSeguido(dao.findEstaSendoSeguido(u, user));
				addGravatarUrl(u, udto);
				usersDto.add(udto);
			}
		
		return usersDto;
	}

	private void addGravatarUrl(Usuario u, UsuarioDto udto) {
		try {
			udto.setGravatarUrl(pictureFinder.getPictureFromEmail(u.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UsuarioDto> findSeguidores(Usuario user) {
		user = dao.findByUsuario(user);
		List<Usuario> users = dao.findSeguidores(user);
		List<UsuarioDto> usersDto = new ArrayList<UsuarioDto>();
		
		if (users != null)
			for (Usuario u : users) {
				UsuarioDto udto = new UsuarioDto(u.getUsername(),u.getNome());
				udto.setSeguindo(dao.findEstaSendoSeguido(u, user));
				udto.setSeguido(true);
				addGravatarUrl(u, udto);
				usersDto.add(udto);
			}
		
		return usersDto;
	}

}
