package com.chorus.service;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.dao.ChorusDao;
import com.chorus.dto.ChoruDto;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;
import com.chorus.util.DatetimeUtil;
import com.chorus.util.ProfilePictureFinder;

@Component
public class ChorusServiceImpl implements ChorusService {

	private ChorusDao dao;
	private UsuarioService usuarioService;
	private ProfilePictureFinder pictureFinder;
	
	private static final String DEFAULT_IMAGE = "images/defaultimage.jpg";
	
	public ChorusServiceImpl(ChorusDao chorusDao, UsuarioService usuarioService, ProfilePictureFinder pictureFinder) {
		this.usuarioService = usuarioService;
		this.dao = chorusDao;
		this.pictureFinder = pictureFinder;
	}
	
	@Override
	public List<Chorus> loadAll() {
		return dao.loadAll();
	}

	@Override
	public void chorar(Chorus chorus) throws Exception {
		
		Usuario usuario = (Usuario) usuarioService.findByUsuario(chorus.getUsuario());
		chorus.setUsuario(usuario);
		
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
			try {
				chorusDto.add(new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
						c.getUsuario().getNome(), pictureFinder.getPictureFromEmail(c.getUsuario().getEmail()),
						DatetimeUtil.timeAgo(c.getDatahora())));
			} catch (Exception e) {
				chorusDto.add(new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
						c.getUsuario().getNome(), DEFAULT_IMAGE, DatetimeUtil.timeAgo(c.getDatahora())));
			}
		}
		
		return chorusDto;
	}
	
	public List<ChoruDto> retrieveTimeline(Usuario usuario) {
		List<Chorus> chorus = dao.retrieveTimeline(usuario);
		List<ChoruDto> chorusDto = new ArrayList<ChoruDto>();
		
		for (Chorus c : chorus) {
			try {
				chorusDto.add(new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
						c.getUsuario().getNome(), pictureFinder.getPictureFromEmail(c.getUsuario().getEmail()),
						DatetimeUtil.timeAgo(c.getDatahora())));
			} catch (Exception e) {
				chorusDto.add(new ChoruDto(c.getMensagem(), c.getUsuario().getUsername(),
						c.getUsuario().getNome(), DEFAULT_IMAGE, DatetimeUtil.timeAgo(c.getDatahora())));
			}
		}
		
		return chorusDto;
	}

}
