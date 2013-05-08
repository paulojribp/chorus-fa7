package com.chorus.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.chorus.entity.Chorus;
import com.chorus.entity.Seguir;
import com.chorus.entity.Usuario;

@Component
public class ChorusDao extends GenericDao<Chorus> {

	@SuppressWarnings("unchecked")
	public List<Chorus> findByUsuario(Usuario usuario) {
		String sql = "select chorus from Chorus chorus inner join chorus.usuario user where user.username = ?";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, usuario.getUsername());
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Chorus> retrieveTimeline(Usuario usuario) {
		String sqlSeguir = "from Seguir s where s.usuario.id = ?";
		List<Seguir> seguindo = entityManager.createQuery(sqlSeguir).setParameter(1, usuario.getId()).getResultList();
		
		List<Usuario> users = new ArrayList<Usuario>();
		for (Seguir s : seguindo) {
			users.add(s.getSeguindo());
		}
		users.add(usuario);
		
		Session session = (Session) entityManager.getDelegate();
		Criteria cri = session.createCriteria(Chorus.class);
		cri.add(Restrictions.in("usuario", users));
		cri.addOrder(Order.desc("datahora"));
		
		return cri.list();
	}
	
}
