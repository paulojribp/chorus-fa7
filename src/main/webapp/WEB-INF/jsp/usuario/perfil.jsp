<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

	<script src="../js/usuario/perfil.js"></script>
	<script>
		UsuarioPerfil.avatar = '${usuarioDto.gravatarUrl }';
	</script>
	
        <div class="container">
            <div class="container-fluid main">
			  <div class="row-fluid">
			    <div class="span3">
					<ul class="nav nav-tabs nav-stacked">
						<li><a href="../timeline/index" class="">Chorinhos<i class="icon-chevron-right pull-right"></i></a></li>
						<li><a href="../usuario/seguindo" class="">Seguindo<i class="icon-chevron-right pull-right"></i></a></li>
						<li><a href="#" class="">Seguidores<i class="icon-chevron-right pull-right"></i></a></li>
					</ul>
				</div>
			    <div class="span9">
			    	<div class="hero-unit">
			    		<div style="margin-left: auto; margin-right: auto; width: 20%">
			    			<img src="${usuarioDto.gravatarUrl }" />
			    		</div>
			    		<div style="margin-left: auto; margin-right: auto; width: 20%; text-align: center;">
					    	<p style="font-weight: bold;">${usuarioDto.nome }</p>
					    	<p style="margin-top: -12px">@${usuarioDto.username }</p>
			    		</div>
			    		<div class="clearfix"></div>
			    	</div>
			    	<div class="well">
			    		<h3>Chorinhos de ${usuarioDto.nome }</h3>
			    		<div id="chorinhos-list" class="row">
			    			
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