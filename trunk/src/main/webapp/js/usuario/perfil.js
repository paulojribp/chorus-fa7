$(document).ready(function () {
	$("#btn-follow").click(function() {
		console.log('Following');
		Chorus.follow(UsuarioPerfil.perfilUsername(), function() {
			$("#btn-follow").hide();
			$("#btn-unfollow").show();
		});
	});
	$("#btn-unfollow").click(function() {
		console.log('Unfollowing');
		Chorus.unfollow(UsuarioPerfil.perfilUsername(), function() {
			$("#btn-follow").show();
			$("#btn-unfollow").hide();
		});
	});
	$("#btn-follow").hide();
	$("#btn-unfollow").hide();
	
	UsuarioPerfil.listarChorus();
	
	Chorus.callbackFunction = UsuarioPerfil.btnFollowControl;
	
});


var UsuarioPerfil = {};

UsuarioPerfil.btnFollowControl = function() {
	if (UsuarioPerfil.seguido == 'true') {
		$("#btn-unfollow").show();
	} else {
		if (UsuarioPerfil.perfilUsername() !== $("#logged-username").attr('username')) {
			$("#btn-follow").show();
		}
	}
};

UsuarioPerfil.perfilUsername = function() {
	var idx = window.location.pathname.lastIndexOf("/");
	return window.location.pathname.substr(idx+1);
};

UsuarioPerfil.listarChorus = function() {
	$.ajax({
		url: '../timeline/listar/' + UsuarioPerfil.perfilUsername(),
		method: 'GET',
		success: function(data) {
			var lista = data.list;
			var chorinhosList = $("#chorinhos-list");
			$(".span12.chorus").remove();
			
			for (var x = 0, size = lista.length; x < size; x++) {
				var chorus = lista[x];
				var template = UsuarioPerfil.chorusTemplate;
				template = template.replace(UsuarioPerfil.AVATAR, UsuarioPerfil.avatar);
				template = template.replace(/%USERNAME%/g, chorus.username);
				template = template.replace(UsuarioPerfil.NOMEUSUARIO, chorus.nome);
				template = template.replace(UsuarioPerfil.CHORUSTIME, chorus.datahora);
				template = template.replace(UsuarioPerfil.CHORUSMENSAGEM, chorus.mensagem);
				
				chorinhosList.append(template);
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};

UsuarioPerfil.NOMEUSUARIO = '%NOME_USUARIO%';
UsuarioPerfil.USERNAME = '%USERNAME%';
UsuarioPerfil.CHORUSTIME = '%CHORUS_TIME%';
UsuarioPerfil.CHORUSMENSAGEM = '%CHORUS_MENSAGEM%';
UsuarioPerfil.AVATAR = '%AVATAR%';

UsuarioPerfil.chorusTemplate = '<li id="chorinhos-'+UsuarioPerfil.USERNAME+'" class="media">' +
								'<a class="pull-left" href="#">' +
								  '<img class="media-object img-polaroid" src="../images/defaultuser.png">' +
								'</a>' +
								'<div class="media-body">' +
								'<div class="row-fluid">' +
									'<span class="nome">' +
										UsuarioPerfil.NOMEUSUARIO +
									'</span>' +
									'<span class="username">' +
										'<a href="../usuario/'+UsuarioPerfil.USERNAME+'" class="span-username">@'+UsuarioPerfil.USERNAME+'</a>' +
									'</span>' +
									'<span class="tempo">' +
										UsuarioPerfil.CHORUSTIME +
									'</span>' +
								'</div>' +
									'<span>'+UsuarioPerfil.CHORUSMENSAGEM+'</span>' +
								'</div>' +
							   '</li>';
