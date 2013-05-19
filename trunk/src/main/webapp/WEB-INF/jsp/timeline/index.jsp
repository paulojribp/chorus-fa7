<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

	<script src="../js/timeline/index.js"></script>
	
        <div class="container">
            <div class="container-fluid main">
			  <div class="row-fluid">
			    <%@include file="/WEB-INF/jsp/barraNavegacao.jsp" %>
			    <div class="span9">
			    	<div class="well">
				    	<form action="#" method="post">
					  	  <div class="control-group">
						    <div class="controls" >
						      <span class="alert alert-info hide span8" id="escrever-chorinho-msg"></span>
	  				    	  <textarea rows="2" id="escrever-chorinho" name="chorus.mensagem" ></textarea>
						    </div>
					  	  </div>
					  	  <div class="control-group">
					  	  	<div id="chorinho-length" class="span10 text-right" style="padding-top: 6px;">144</div>
						  	<div class="controls text-right">
						    	<button class="btn btn-primary" id="btn-chorar" type="button">Chorar</button>
						  	</div>
					  	  </div>
					  	</form>
			    	</div>
			    	<div class="well">
			    		<h3>Chorinhos</h3>
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