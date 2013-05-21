package com.chorus.aceitacao;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class chorarTeste extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://54.243.139.54/");
		selenium.start();
	}

	@Test
	public void testChorarTeste() throws Exception {
		selenium.open("/chorus/index/index");
		selenium.type("id=loginUsername", "flavioso");
		selenium.type("id=loginSenha", "123456");
		selenium.click("id=btn-usuario-login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=escrever-chorinho", "teste tricampeao...");
		selenium.click("id=btn-chorar");
		selenium.click("link=Chorinhos");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
