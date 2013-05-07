package com.chorus.auth;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.chorus.entity.Usuario;

@Component
@SessionScoped
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 5429898654602729550L;
	
	private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void login(Usuario user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }
    
}
