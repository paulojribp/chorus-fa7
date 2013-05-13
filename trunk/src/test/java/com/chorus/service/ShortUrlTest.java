package com.chorus.service;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chorus.util.ShortUrl;

public class ShortUrlTest {
	
	private ShortUrl url;
	
	@Before
	public void setUp(){
		url = new ShortUrl();
	}
	
	@Test
	public void testEnviarMensagemSemUrl() throws Exception {
		assertEquals("Texto de exemplo", url.formataTexto("Texto de exemplo"));
	}

	@Test( expected = Exception.class)
	public void testEnviarMensagemNula() throws Exception{
		try {
			url.formataTexto(null);
			fail("Deve Retornar uma Exceção de Texto Invalido!");
		} catch (Exception e) {
			assertEquals("Texto é Requerido", e.getMessage());
			throw e;
		}
		
	}
	
	@Test( expected = Exception.class)
	public void testEnviarMensagemVazia() throws Exception{
		try {
			url.formataTexto("");
			fail("Deve Retornar uma Exceção de Texto Invalido!");
		} catch (Exception e) {
			assertEquals("Texto é Requerido", e.getMessage());
			throw e;
		}
		
	}
}
