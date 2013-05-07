<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Chorus</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="../css/bootstrap.css">
        <link rel="stylesheet" href="../css/estilo.css">
        <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
                background-image: url("../images/cidade.png");
            }
        </style>
        <script src="../js/vendor/jquery-1.9.1.min.js"></script>
        <script src="../js/chorus.js"></script>
    </head>
    <body>
    	<c:out value="${basePath}" />
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

	<div class="navbar navbar-inverse navbar-fixed-top">
	    <div class="navbar-inner">
	        <div class="container">
	            <a class="brand" href="#">Chorus</a>
	            <div class="nav-collapse collapse pull-right">
	            	<div id="user-data" class="hide logged-user">
	            		<div class="span4 text-right" id="logged-username"> </div><a href="../usuario/logout">Logout</a>
	            	</div>
	            	
	            	<div id="login-form" class="hide">
	            		<form class="navbar-form pull-right" method="post" action="../usuario/login">
		                	<c:if test="${not empty error}">
			                	<span id="loginErrorMsg" class="alert alert-error">
			                		${error.message}
			                	</span>
							</c:if>
		                    <input id="loginUsername" name="usuario.username" class="span2" type="text" placeholder="Username">
		                    <input id="loginSenha" name="usuario.senha" class="span2" type="password" placeholder="Password">
		                    <button id="btn-usuario-login" type="submit" class="btn" >Acessar</button>
		                </form>
		            </div>
	            </div>
	        </div>
	    </div>
	</div>

	