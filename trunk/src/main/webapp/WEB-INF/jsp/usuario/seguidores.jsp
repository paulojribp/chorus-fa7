<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

	<script src="../js/usuario/seguidores.js"></script>
	
        <div class="container">
            <div class="container-fluid main">
			  <div class="row-fluid">
			    <%@include file="/WEB-INF/jsp/barraNavegacao.jsp" %>
			    <div class="span9">
			    	<div class="well">
			    		<h3>Seguidores</h3>
			    		<ul id="chorinhos-list" class="media-list">
			    			
			    		</ul>
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