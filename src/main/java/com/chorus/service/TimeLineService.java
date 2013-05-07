package com.chorus.service;

import java.util.List;

import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;

public interface TimeLineService {

	/**
	 * @param user
	 * @param chorus
	 * @return
	 * @throws Exception 
	 */
	Chorus publicarNaTimeLine(Chorus chorus) throws Exception;

	/**
	 * @param usuario
	 * @throws Exception 
	 */
	List<Chorus> listar(Usuario usuario) throws Exception;
	
	/**
	 * 
	 * @return all chorus
	 */
	List<Chorus> loadAll();

}
