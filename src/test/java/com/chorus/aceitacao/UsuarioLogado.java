package com.chorus.aceitacao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.SeleneseTestCase;

public class UsuarioLogado extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://54.243.139.54/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testUsuarioLogado() throws Exception {
		selenium.open("/chorus/index/index");
		selenium.type("id=loginUsername", "flavioso");
		selenium.type("id=loginSenha", "123456");
		selenium.click("id=btn-usuario-login");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
