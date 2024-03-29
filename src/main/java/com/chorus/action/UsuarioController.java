package com.chorus.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

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
import com.chorus.util.ProfilePictureFinder;

@Resource
@Path("/usuario")
public class UsuarioController {

	private Result result;
	
	private UsuarioService service;
	private UserInfo userInfo;

	private ProfilePictureFinder pictureFinder;
	
	public UsuarioController(UsuarioService usuarioService, Result result, UserInfo userInfo, ProfilePictureFinder pictureFinder) {
		this.service = usuarioService;
		this.result = result;
		this.userInfo = userInfo;
		this.pictureFinder = pictureFinder;
	}

	@Post
	@Path("/login")
    public void login(Usuario usuario) {
		try {
			usuario = service.login(usuario);
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
	@Path(value="/logout", priority=1)
	public void logout() {
		userInfo.logout();
		result.redirectTo(IndexController.class).index();
	}
	
	@Post
	@Path("/salvar")
	public void salvar(UsuarioDto usuario) throws Exception {
		ReturnDto returndto = new ReturnDto();
		try {
			service.salvar(usuario);
			returndto.setSuccess(true);
		} catch (Exception e) {
			returndto.setSuccess(false);
			returndto.setMessage(e.getMessage());
		}
		
		result.use(Results.json()).from(returndto).serialize();
	}

//	@Post
//	@Path("/seguir")
//	public void seguir(@RequestParam Long usuarioASeguirId) throws ErroAoSeguirException {
//		service.seguir(userInfo.getUser().getId(), usuarioASeguirId);
//		result.nothing();
//	}
	
	@Post
	@Path("/seguir")
	public void seguir(String username) throws ErroAoSeguirException {
		service.seguir(userInfo.getUser().getId(), username);
		result.nothing();
	}
	
	@Post
	@Path("/deixarSeguir")
	public void deixarSeguir(String username) throws ErroAoSeguirException {
		service.deixarSeguir(userInfo.getUser().getId(), username);
		result.nothing();
	}
	
	@Path(value="/seguindo", priority=1)
	public void seguindo() {
		
	}
	
	@Post
	public void listarSeguindo() {
		List<UsuarioDto> users = service.findSeguindo(userInfo.getUser());
		result.use(Results.json()).from(users).serialize();
	}
	
	@Post
	public void listarSeguidores(){
		List<UsuarioDto> users = service.findSeguidores(userInfo.getUser());
		result.use(Results.json()).from(users).serialize();
	}

	@Post
	@Path("/loggedUser")
	public void loggedUser() throws MalformedURLException, IOException {
		UsuarioDto u = new UsuarioDto();
		
		Usuario usuario = userInfo.getUser();
		if (usuario != null) {
			u = new UsuarioDto(usuario.getUsername(), usuario.getNome());
		}
		result.use(Results.json()).from(u).serialize();
	}
	
	@Post
	@Path("/userPhoto")
	public void loadUserPhoto(String email) {
		if (email == null || email.trim().length() == 0) {
			email = userInfo.getUser().getEmail();
		}
		
		try {
			String imgurl = pictureFinder.getPictureFromEmail(email);
			result.use(Results.json()).from(imgurl).serialize();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			result.nothing();
		}
	}
	

	@Path(value="/seguidores", priority=1)
	public void seguidores() {
		
	}
	
	@Get
	@Path(value="/{username}", priority=2)
	public UsuarioDto perfil(@RequestParam String username) {
		Usuario u = new Usuario(username);
		UsuarioDto udto = service.findByUsuario(u);
		try {
			udto.setGravatarUrl(pictureFinder.getPictureFromEmailDefaultSize(udto.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return udto;
	}

}
