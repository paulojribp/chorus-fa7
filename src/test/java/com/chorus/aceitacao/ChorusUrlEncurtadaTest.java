package com.chorus.aceitacao;

import org.junit.Test;

public class ChorusUrlEncurtadaTest extends AbstractSeleniumChorusTest{
	
	@Test
	public void testChorusUrlEncurtada() throws Exception {
		login("kete", "123456");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=escrever-chorinho", "http://wwww.fa7.edu.br/teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		selenium.click("id=btn-chorar");
		selenium.click("link=Chorinhos");
		selenium.waitForPageToLoad("30000");
	}
}
