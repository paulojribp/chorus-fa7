<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>
        <div class="container">
            <div class="container-fluid">
			  <div class="row-fluid">
			    <div class="span3">
			      <ul class="nav nav-tabs nav-stacked">
  					<li><a href="#" class="">Chorinhos<i class="icon-chevron-right pull-right"></i></a></li>
  					<li><a href="#" class="">Seguindo<i class="icon-chevron-right pull-right"></i></a></li>
  					<li><a href="#" class="">Seguidores<i class="icon-chevron-right pull-right"></i></a></li>
				  </ul>
			    </div>
			    <div class="span8">
			    	<div class="well">
				    	<form action="<c:url value="/timeline/publicar" />" method="post">
						    <legend>Escrevendo chorinho</legend>
						  <div class="control-group">
						    <label class="control-label" for="Nome">Nome</label>
						    <div class="controls">
	  				    	  <input  type="text" id="nome" name ="chorus.usuario.username" placeholder="Nome">
						    </div>
					  	  </div>
					  	  <div class="control-group">
						    <label class="control-label" for="Nome">Chorinho</label>
						    <div class="controls">
	  				    	  <textarea rows="3" id="chorinho" name="chorus.mensagem" ></textarea>
						    </div>
					  	  </div>
					  	  <div class="control-group">
						  	<div class="controls">
						    	<button class="btn btn-primary" type="submit">Enviar</button>
						  	</div>
					  	  </div>
					  	</form>
			    	</div>
			    	<div class="well">
			    		<h3>Chorinhos</h3>
			    		<table class="table chorus-table-hover">
			    			<c:forEach var="chorus" items="${chorinhos}">
					    		<tr>
					    			<td>
									    <a class="pull-left" href="#">
									      <img class="media-object" data-src="../images/thumb64x64.png">
									    </a>
									    <div class="media-body">
									    	<h4 class="media-heading">${chorus.usuario.username}</h4>
									    	<p>${chorus.mensagem}</p>
									    </div>
								    </td>
							     </tr>
							 </c:forEach> 
			    		</table>
			    	</div>
			    </div>
			  </div>
			</div>
            <hr>

            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        </div> 

        <script src="../js/vendor/bootstrap.min.js"></script>

    </body>
</html>