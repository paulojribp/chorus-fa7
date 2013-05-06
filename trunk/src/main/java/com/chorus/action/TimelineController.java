package com.chorus.action;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;
import com.chorus.service.ChorusService;

@Resource
@Path("/timeline")
public class TimelineController {

	private Result result;

	private ChorusService chorusService;

	public TimelineController(ChorusService chorusService, Result result) {
		this.chorusService = chorusService;
		this.result = result;
	}

	public void publicar(Chorus chorus) throws Exception {
		chorusService.chorar(chorus);
		result.redirectTo(TimelineController.class).listar(chorus.getUsuario());
	}


	@Path("/listar/{usuario.username}")
	@Get
	public void listar(Usuario usuario) throws Exception {
		List<Chorus> chorinhos = null;
		if(usuario == null){
			chorinhos = chorusService.loadAll();
		}else{
			chorinhos = chorusService.findByUsuario(usuario);
		}
		
		result.include("chorinhos", chorinhos);
		result.redirectTo(TimelineController.class).listar();
	}

	public void listar() throws Exception {
//		result.include("chorinhos", chorusService.loadAll());
	}
}
