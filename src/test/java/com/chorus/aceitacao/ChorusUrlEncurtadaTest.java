package com.chorus.aceitacao;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class ChorusUrlEncurtadaTest {
	
	private static WebDriver driver;
	private static Selenium selenium;
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver,"http://54.243.139.54/");
		driver.get("http://54.243.139.54/");	
	}

	@Test
	public void testChorusUrlEncurtada() throws Exception {
		selenium.open("/chorus/index/index");
		selenium.type("id=loginUsername", "flavioso");
		selenium.type("id=loginSenha", "123456");
		selenium.click("id=btn-usuario-login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=escrever-chorinho", "http://wwww.fa7.edu.br/teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		selenium.click("id=btn-chorar");
		selenium.click("link=Chorinhos");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
