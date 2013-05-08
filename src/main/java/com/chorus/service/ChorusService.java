package com.chorus.service;

import java.util.List;

import com.chorus.dto.ChoruDto;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;

public interface ChorusService {

	List<Chorus> loadAll();
	
	void chorar(Chorus chorus) throws Exception;
	
	List<ChoruDto> findByUsuario(Usuario usuario);
	
	List<ChoruDto> retrieveTimeline(Usuario usuario);
	
}
