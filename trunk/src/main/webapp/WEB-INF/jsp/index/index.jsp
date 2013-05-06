<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

     <div class="container">
		  <div class="row-fluid">
		    <div class="span8">
		      <h3 class="text1">Seja bem vindo ao Chorus!</h3><br />
		      <h4 class="text1">Sua nova maneira de postar textos rápidos, pensamentos, frases e etc...</h4>
		    </div>
		    <div class="span4 well">
		      <div>
		      	<legend>Cadastrar Usuario</legend>
					<form class="form-horizontal" action="<c:url value="/usuario/salvar" />" method="post">
  				    	  <input class='span11' type="text" id="username" name ="usuario.username" placeholder="Nome" >
  				    	  <input class='span11' type="text" id="email" name ="usuario.email" placeholder="Email" >
  				    	  <input class='span11' type="text" id="login" name ="usuario.login" placeholder="Login" >
  				    	  <input class='span11' type="password" id="senha" name ="usuario.senha" placeholder="Senha" >
					      <button class="btn btn-primary span6" type="submit">Salvar</button>
				  	  </div>
				  </form>	
		      
		      </div>
		    </div>
		  </div>
		</div>
            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        <script src="../js/vendor/bootstrap.min.js"></script>
    </body>
</html>