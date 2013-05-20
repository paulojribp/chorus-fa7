package com.chorus.action;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.chorus.auth.UserInfo;
import com.chorus.dto.ChoruDto;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;
import com.chorus.service.ChorusService;

@Resource
@Path("/timeline")
public class TimelineController {

	private Result result;

	private ChorusService chorusService;
	
	private UserInfo userInfo;

	public TimelineController(ChorusService chorusService, Result result, UserInfo userInfo) {
		this.chorusService = chorusService;
		this.result = result;
		this.userInfo = userInfo;
	}

	public void publicar(Chorus chorus) throws Exception {
		chorus.setUsuario( userInfo.getUser() );
		chorusService.chorar(chorus);
		result.use(Results.json());
	}


	@Path("/listar/{usuario.username}")
	@Get
	public void listar(Usuario usuario) throws Exception {
		List<ChoruDto> chorinhos = chorusService.findByUsuario(usuario);
		result.use(Results.json()).from(chorinhos).serialize();
	}
	
	@Path("/index")
	public void index() {
		
	}

	@Path("/listar")
	public void listar() throws Exception {
		List<ChoruDto> chorus = chorusService.retrieveTimeline(userInfo.getUser());
		result.use(Results.json()).from(chorus).serialize();
	}
	
}

