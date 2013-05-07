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

<c:choose>
	<c:when test="${not empty usuarioLogado}">
		<div class="navbar navbar-inverse navbar-fixed-top">
		    <div class="navbar-inner">
		        <div class="container">
		            <a class="brand" href="#">Chorus</a>
		            <div class="nav-collapse collapse">
		            	<label class="label label-success">Usuário: ${usuarioLogado.usuario.username} </label>
		            </div>
		        </div>
		    </div>
		</div>
	</c:when>
	
	<c:otherwise>
		<div class="navbar navbar-inverse navbar-fixed-top">
		    <div class="navbar-inner">
		        <div class="container">
		            <a class="brand" href="#">Chorus</a>
		            <div class="nav-collapse collapse">
		                <form class="navbar-form pull-right">
		                	<c:if test="${not empty error}">
			                	<label id="loginErrorMsg" class="label label-important">${error}</label>
							</c:if>
		                    <input id="loginUsername" class="span2" type="text" placeholder="Email">
		                    <input id="loginSenha" class="span2" type="password" placeholder="Password">
		                    <button id="btn-usuario-login" type="submit" class="btn" >Acessar</button>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
	</c:otherwise>
</c:choose>

	