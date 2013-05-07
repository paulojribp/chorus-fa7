package com.chorus.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chorus.dao.ChorusDao;
import com.chorus.dao.TimelineDao;
import com.chorus.entity.Chorus;
import com.chorus.entity.Usuario;

public class PublicarTimeLineTest {

	private static TimeLineService	service;

	@BeforeClass
	public static void beforeClass() {
		
		ChorusDao chorusDao = mock(ChorusDao.class);
		TimelineDao timelineDao = mock(TimelineDao.class);;
		service = new TimeLineServiceImpl(timelineDao, chorusDao);
		
	}

	
	@Test(expected = Exception.class)
	public void publicarNaTimeLineComUsuarioNull() throws Exception {
		Chorus chorus = new Chorus();
		Usuario usuario = new Usuario();
		chorus.setUsuario(usuario);
		Chorus publicado = service.publicarNaTimeLine(chorus);
		assertEquals(publicado, chorus);
	}
	
	@Test
	public void publicarNaTimeLineComUsuarioValido() throws Exception {
		Chorus chorus = new Chorus();
		Usuario usuario = new Usuario();
		usuario.setUsername("Chorao");
		chorus.setUsuario(usuario);
		chorus.setMensagem("In above example, the divisionWithException() method will throw an ArithmeticException Exception, since this is an expected exception.");
		Chorus publicado = service.publicarNaTimeLine(chorus);
		assertEquals(publicado, chorus);
	}

	@Test(expected = Exception.class)
	public void publicarNaTimeLineComMensagemVazia() throws Exception {
		Chorus chorus = new Chorus();
		Usuario usuario = new Usuario();
		usuario.setUsername("Chorao");
		chorus.setUsuario(usuario);
		Chorus publicado = service.publicarNaTimeLine(chorus);
		assertEquals(publicado, chorus);
	}

	@Test(expected = Exception.class)
	public void publicarNaTimeLineComMensagemMaiorQue144() throws Exception {
		Chorus chorus = new Chorus();
		Usuario usuario = new Usuario();
		usuario.setUsername("Chorao");
		chorus.setUsuario(usuario);
		chorus.setMensagem("In above example, the divisionWithException() method will throw an ArithmeticException Exception, since this is an expected exception, so the unit test will pass.");
		Chorus publicado = service.publicarNaTimeLine(chorus);
		assertEquals(publicado, chorus);
	}
	
	
	
}
