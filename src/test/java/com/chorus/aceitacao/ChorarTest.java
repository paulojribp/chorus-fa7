package com.chorus.aceitacao;

import org.junit.Test;

public class ChorarTest extends AbstractSeleniumChorusTest {
	

	@Test
	public void testChorarTeste() throws Exception {
		login("kete", "123456");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=escrever-chorinho", "teste tricampeao...");
		selenium.click("id=btn-chorar");
		selenium.click("link=Chorinhos");
		selenium.waitForPageToLoad("30000");
	}

}
