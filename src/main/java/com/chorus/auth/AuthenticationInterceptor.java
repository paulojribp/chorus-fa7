package com.chorus.auth;

import static br.com.caelum.vraptor.view.Results.logic;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.chorus.action.IndexController;
import com.chorus.action.UsuarioController;
import com.chorus.dto.ReturnDto;
import com.chorus.service.UsuarioService;

@Intercepts
public class AuthenticationInterceptor implements Interceptor {

	private final UserInfo userInfo;
	private final UsuarioService usuarioService;
	private final Result result;
	
	@SuppressWarnings("unchecked")
	private final List<Class<?>> allowedClasses = Arrays.asList(UsuarioController.class, IndexController.class);
	
	public AuthenticationInterceptor(UserInfo userInfo, UsuarioService usuarioService, Result result) {
		this.userInfo = userInfo;
		this.usuarioService = usuarioService;
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod resourceMethod) {
		return notLogin(resourceMethod) && notNewUser(resourceMethod);
	}
	
	private boolean notNewUser(ResourceMethod method) {
        Method invokedMethod = method.getMethod();
        if (invokedMethod.getDeclaringClass().equals(UsuarioController.class)) {
            return !"salvar".equals(invokedMethod.getName());
        }
        return true;
    }
	
	private boolean notLogin(ResourceMethod method) {
		for (Class<?> clazz : allowedClasses) {
			if (method.getMethod().getDeclaringClass().equals(clazz)) {
				return false;
			}
		}
		
        return true;
    }

	@Override
	public void intercept(InterceptorStack interceptorStack, ResourceMethod resourceMethod,
			Object object) throws InterceptionException {
		
		if (userInfo == null || userInfo.getUser() == null) {
			result.include("errors", new ReturnDto(false, "Usuário não está autenticado no sistema"));
			result.use(logic()).redirectTo(IndexController.class).index();
		} else {
			usuarioService.refresh(userInfo.getUser());
			// continues execution
	    	interceptorStack.next(resourceMethod, object);
		}
		
	}

}
