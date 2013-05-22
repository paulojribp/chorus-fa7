/*
 * Copyright 2012 Sagarana Tech.  All rigths reserved.
 *
 * This software is the confidential and proprietary information of
 * Sagarana Tech ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Sagarana Tech.
 */
package com.chorus.aceitacao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public abstract class AbstractSeleniumChorusTest {

	protected static WebDriver driver;
	protected static Selenium selenium;
	
	@BeforeClass
	public static void setUp(){
		driver = new FirefoxDriver();
		String server = "http://localhost:8081/chorus/";
		selenium = new WebDriverBackedSelenium(driver,server);
		driver.get(server);	
	}
	
	@AfterClass
	public static void  tearDown() throws Exception {
		selenium.stop();
	}
	
	protected void login(String login, String senha) {
		selenium.open("index/index");
		selenium.type("id=loginUsername", login);
		selenium.type("id=loginSenha", senha);
		selenium.click("id=btn-usuario-login");
		
	}
}
