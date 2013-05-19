package com.chorus.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.dao.ChorusDao;
import com.chorus.dto.ChoruDto;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;
import com.chorus.util.DatetimeUtil;
import com.chorus.util.ShortUrl;

@Component
public class ChorusServiceImpl implements ChorusService {

	private ChorusDao dao;
	private UsuarioService usuarioService;
	
	private static final String DEFAULT_IMAGE = "../images/defaultuser.png";
	
	public ChorusServiceImpl(ChorusDao chorusDao, UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
		this.dao = chorusDao;
	}
	
	@Override
	public List<Chorus> loadAll() {
		return dao.loadAll();
	}

	@Override
	public void chorar(Chorus chorus) throws Exception {
		
		ShortUrl url = new ShortUrl();
		
		Usuario usuario = (Usuario) usuarioService.findByUsuario(chorus.getUsuario());
		chorus.setUsuario(usuario);
		chorus.setDatahora(Calendar.getInstance());
		chorus.setMensagem(url.formataTexto(chorus.getMensagem()));
		
		String mensagem = chorus.getMensagem();
		
		if(mensagem == null || mensagem.trim().isEmpty()){
			throw new Exception("Mensagem nao pode ser vazia.");
		}

		if(mensagem != null && mensagem.trim().length() > 144){
			throw new Exception("Mensagem nao pode exceder 144 caracteres.");
		}
		
		dao.create(chorus);
	}

	@Override
	public List<ChoruDto> findByUsuario(Usuario usuario) {
		List<Chorus> chorus = dao.findByUsuario(usuario);
		List<ChoruDto> chorusDto = new ArrayList<ChoruDto>();
		
		for (Chorus c : chorus) {
			chorusDto.add(new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
					c.getUsuario().getNome(), c.getUsuario().getEmail(), DEFAULT_IMAGE,
					DatetimeUtil.timeAgo(c.getDatahora())));
		}
		
		return chorusDto;
	}
	
	public List<ChoruDto> retrieveTimeline(Usuario usuario) {
		List<Chorus> chorus = dao.retrieveTimeline(usuario);
		List<ChoruDto> chorusDto = new ArrayList<ChoruDto>();
		
		for (Chorus c : chorus) {
			chorusDto.add( new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
					c.getUsuario().getNome(), c.getUsuario().getEmail(), DEFAULT_IMAGE,
					DatetimeUtil.timeAgo(c.getDatahora()) ));
		}
		
		return chorusDto;
	}

}
