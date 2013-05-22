<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

	<script src="../js/index/index.js"></script>
	
     <div class="container">
		  <div class="row-fluid">
		    <div class="span8">
		      <h3 class="text1">Seja bem vindo ao Chorus!</h3><br />
		      <h4 class="text1">Sua nova maneira de postar textos rápidos, pensamentos, frases e etc...</h4>
		    </div>
		    <div class="span4 well">
		      <div>
		      	<legend>Cadastrar Usuario</legend>
		      		<div class="alert" id="cadastro-msg">
		      			Teste
		      		</div>
					<form class="form-horizontal" action="<c:url value="/usuario/salvar" />" method="post">
  				    	  <input class='span11' type="text" id="nome" placeholder="Nome" >
  				    	  <input class='span11' type="text" id="email" placeholder="Email" >
  				    	  <input class='span11' type="text" id="username" placeholder="Username" >
  				    	  <input class='span11' type="password" id="senha" placeholder="Senha" >
  				    	  <input class='span11' type="password" id="confirmasenha" placeholder="Confirmar Senha" >
					      <button class="btn btn-primary span6" id="btn-cadastrar" type="button">Salvar</button>
					</form>
		      </div>
		    </div>
		  </div>
		</div>
		<center>
			<div class="footer">
	           &copy; Chorus 2013
			</div>
		</center>
        <script src="../js/vendor/bootstrap.min.js"></script>
    </body>
</html>