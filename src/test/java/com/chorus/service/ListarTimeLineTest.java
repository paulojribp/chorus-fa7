package com.chorus.service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.chorus.dao.ChorusDao;
import com.chorus.dao.TimelineDao;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;

/**
 * 
 * @author kete@sagaranatech.com
 * @since Apr 15, 2013
 * @version $Revision:  $ <br>
 *          $Date:  $ <br> 
 *          $Author:  $
 */
@Ignore
public class ListarTimeLineTest {
	
	private static TimeLineService	service;
	
	@BeforeClass
	public static void beforeClass() {
		ChorusDao chorusDao = mock(ChorusDao.class);
		TimelineDao timelineDao = mock(TimelineDao.class);;
		service = new TimeLineServiceImpl(timelineDao, chorusDao);
	}
	
	@Test(expected=Exception.class)
	public void listarComUsuarioInvalido() throws Exception {
		Usuario usuario = new Usuario();
		service.listar(usuario);
	}

	@Test(expected=Exception.class)
	public void listarComUsuarioQueNaoTemChorus() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("Chorao");
		List<Chorus> chorinhos = service.listar(usuario);
		assertEquals(0, chorinhos.size());
	}
	
	@Test
	public void listarChorusUsuarioQueTemCincoMensagens() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("Chorao");
		int quant = 5;
		publicarMensagens(usuario, quant);
		List<Chorus> chorinhos = service.listar(usuario);
		assertNotNull(chorinhos);
		assertEquals(quant, chorinhos.size());
	}

	private void publicarMensagens(Usuario usuario, int quant) throws Exception {
		int i = 0;
		while (i < quant){
			Chorus chorus = new Chorus();
			chorus.setUsuario(usuario);
			chorus.setMensagem("Mensagem do "+ usuario.getUsername() + " "+ quant);
			service.publicarNaTimeLine(chorus);
			i++;
		}
	}
	
}
