package com.chorus.aceitacao;
import org.junit.Test;

public class LogarUsuario extends AbstractSeleniumChorusTest  {

		@Test
		public void testCadastrarUsuario() throws Exception {
			selenium.open("/chorus/index/index");
			String username =  "flavioso";
			String senha =  "123456";
			selenium.type("id=nome",username);
			selenium.type("id=email", "flaviosof@gmail.c");
			selenium.type("id=username", username);
			selenium.type("id=senha", senha);
			selenium.type("id=confirmasenha", senha);
			selenium.click("id=btn-cadastrar");
			selenium.type("id=nome", "flaviosof");
			selenium.click("id=btn-cadastrar");
			selenium.type("id=username", "flaviosoceara");
			selenium.click("id=btn-cadastrar");
			login(username, senha);
		}

}

	
	
