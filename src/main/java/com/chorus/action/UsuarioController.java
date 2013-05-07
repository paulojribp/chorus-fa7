package com.chorus.action;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.chorus.dto.ReturnDto;
import com.chorus.dto.UsuarioDto;
import com.chorus.exceptions.ErroAoSeguirException;
import com.chorus.service.UsuarioService;
import com.chorus.dto.UsuarioLogado;
import com.chorus.entity.Usuario;

@Resource
@Path("/usuario")
public class UsuarioController {

	private Result result;
	
	private UsuarioService usuarioService;
	private UsuarioLogado usuarioLogado;
	
	public UsuarioController(UsuarioService usuarioService, Result result, UsuarioLogado usuarioLogado) {
		this.usuarioService = usuarioService;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}

	@Post("/login")
    public void login(Usuario usuario) {
		Usuario usuarioPersistido = usuarioService.findByUsuario(usuario);
		if (usuarioPersistido == null) {
			result.include("error", new ReturnDto(false, "usuario nao encontrado"));
			System.out.println("Usuario nao encontrado");
			usuarioLogado = null;
			return;
		}

		if (!usuarioPersistido.getSenha().equals(usuario.getSenha())) {
			result.include("error", new ReturnDto(false, "Senha incorreta"));
			usuarioLogado = null;
			return;
		}
		
		usuarioLogado.logar(usuarioPersistido);
		result.include("usuarioLogado", usuarioLogado);
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
		usuarioService.seguir(1l, usuarioASeguirId);
		result.nothing();
	}

}
