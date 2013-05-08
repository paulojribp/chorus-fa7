<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

	<script src="../js/timeline/index.js"></script>
	
        <div class="container">
            <div class="container-fluid main">
			  <div class="row-fluid">
			    <div class="span3">
			      <ul class="nav nav-tabs nav-stacked">
  					<li><a href="#" class="">Chorinhos<i class="icon-chevron-right pull-right"></i></a></li>
  					<li><a href="#" class="">Seguindo<i class="icon-chevron-right pull-right"></i></a></li>
  					<li><a href="#" class="">Seguidores<i class="icon-chevron-right pull-right"></i></a></li>
				  </ul>
			    </div>
			    <div class="span9">
			    	<div class="well">
				    	<form action="#" method="post">
					  	  <div class="control-group">
						    <div class="controls">
						      <span class="alert alert-info hide" id="escrever-chorinho-msg"></span>
	  				    	  <textarea rows="4" id="escrever-chorinho" name="chorus.mensagem" ></textarea>
						    </div>
					  	  </div>
					  	  <div class="control-group">
						  	<div class="controls text-right">
						    	<button class="btn btn-primary" id="btn-chorar" type="button">Chorar</button>
						  	</div>
					  	  </div>
					  	</form>
			    	</div>
			    	<div class="well">
			    		<h3>Chorinhos</h3>
			    		<div id="chorinhos-list" class="row">
			    			<div class="span12 chorus">
			    				<span class="span-avatar"><img alt="perfil" src="../images/defaultuser.png" class="avatar" /></span>
			    				<span class="span-nome">
			    					Nome Usuario
				    				<span class="span-username">@username</span>
			    				</span>
			    				<span class="span9">Mensagem de Choru</span>
			    			</div>
			    		</div>
			    	</div>
			    </div>
			  </div>
			</div>
            <hr>

            <footer>
                <p>&copy; Chorus 2013</p>
            </footer>
        </div> 

        <script src="../js/vendor/bootstrap.min.js"></script>

    </body>
</html>