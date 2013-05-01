package com.chorus.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.entity.Usuario;


@Component
public class UsuarioDao extends GenericDao<Usuario>{
	
	@SuppressWarnings("unchecked")
	public Usuario findByUsuario(Usuario usuario) {
		String sql = " from Usuario user where user.username = ? ";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, usuario.getUsername());

		Usuario user = null;
		List resultList = q.getResultList();
		if(resultList != null && !resultList.isEmpty()){
			user = (Usuario) resultList.get(0);
		}
		return user;
	}
	
	
}
