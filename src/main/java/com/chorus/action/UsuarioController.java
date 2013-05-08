package com.chorus.action;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.chorus.auth.UserInfo;
import com.chorus.dto.ReturnDto;
import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;
import com.chorus.exceptions.UsuarioInexistenteException;
import com.chorus.service.UsuarioService;

@Resource
@Path("/usuario")
public class UsuarioController {

	private Result result;
	
	private UsuarioService usuarioService;
	private UserInfo userInfo;
	
	public UsuarioController(UsuarioService usuarioService, Result result, UserInfo userInfo) {
		this.usuarioService = usuarioService;
		this.result = result;
		this.userInfo = userInfo;
	}

	@Post
	@Path("/login")
    public void login(Usuario usuario) {
		try {
			usuario = usuarioService.login(usuario);
			userInfo.login(usuario);
			result.include("usuarioLogado", new UsuarioDto(usuario.getUsername(), usuario.getNome()));
			result.redirectTo(TimelineController.class).index();
		} catch (UsuarioInexistenteException e) {
			result.include("error", new ReturnDto(false, "Usuario/Senha inválidos"));
			result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			result.include("error", new ReturnDto(false, "Erro na solicitação do servidor"));
		}
    }
	
	@Get
	@Path("/logout")
	public void logout() {
		userInfo.logout();
		result.redirectTo(IndexController.class).index();
	}
	
	@Post
	@Path("/salvar")
	public void salvar(UsuarioDto usuario) throws Exception {
		ReturnDto returndto = new ReturnDto();
		try {
			usuarioService.salvar(usuario);
			returndto.setSuccess(true);
		} catch (Exception e) {
			returndto.setSuccess(false);
			returndto.setMessage(e.getMessage());
		}
		
		result.use(Results.json()).from(returndto).serialize();
	}

	@Post
	@Path("/seguir")
	public void seguir(Long usuarioASeguirId) throws ErroAoSeguirException {
		usuarioService.seguir(userInfo.getUser().getId(), usuarioASeguirId);
		result.nothing();
	}

	@Post
	@Path("/loggedUser")
	public void loggedUser() {
		UsuarioDto u = new UsuarioDto();
		
		if (userInfo.getUser() != null) {
			u = new UsuarioDto(userInfo.getUser().getUsername(), userInfo.getUser().getNome());
		}
		
		result.use(Results.json()).from(u).serialize();
	}
	
}
