package com.chorus.dao;

import java.util.Calendar;

import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.entity.Seguir;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;


@Component
public class UsuarioDao extends GenericDao<Usuario>{
	
	public Usuario findByUsuario(Usuario usuario) {
		String sql = " from Usuario user where user.username = ? ";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, usuario.getUsername());

		try {
			return (Usuario) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean userAlreadyFollow(Long userId, Long userASeguirId) {
		String sql = "from Seguir se where se.usuario.id = ? and se.seguindo.id = ?";
		
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, userId);
		q.setParameter(2, userASeguirId);
		
		try {
			q.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void seguir(Long userId, Long userASeguirId) throws ErroAoSeguirException {
		Usuario user = entityManager.find(Usuario.class, userId);
		Usuario userASeguir = entityManager.find(Usuario.class, userASeguirId);
		
		Seguir seguir = new Seguir();
		seguir.setUsuario(user);
		seguir.setSeguindo(userASeguir);
		seguir.setDatahora(Calendar.getInstance().getTime());
		
		try {
			entityManager.persist(seguir);
		} catch (Exception e) {
			throw new ErroAoSeguirException("Não é possível seguir o usuário: " + userASeguir.getUsername(), e);
		}
	}

	public void refresh(Usuario user) {
		user = loadById(user.getId());
		entityManager.refresh(user);
	}
	
}
