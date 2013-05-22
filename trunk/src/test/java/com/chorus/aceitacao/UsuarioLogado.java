package com.chorus.aceitacao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class UsuarioLogado {
	
	private static WebDriver driver;
	private static Selenium selenium;
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver,"http://54.243.139.54/");
		driver.get("http://54.243.139.54/");	
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
