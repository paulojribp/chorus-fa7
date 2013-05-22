package com.chorus.aceitacao;

import org.junit.Test;

public class UsuarioLogado extends AbstractSeleniumChorusTest {

	@Test
	public void testUsuarioLogado() throws Exception {
		login("kete", "123456");
		selenium.waitForPageToLoad("30000");
	}
}
