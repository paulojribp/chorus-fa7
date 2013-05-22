var Chorus = {};
var Perfil = {};

Chorus.callbackFunction = undefined;
Chorus.verifyUserLogin = function() {
	$.ajax({
		url: '../usuario/loggedUser',
		method: 'POST',
		success: function(data) {
			var user = data.usuarioDto;
			
			if (user.username !== undefined) {
				$("#logged-username").text(user.nome);
				$("#logged-username").attr('username', user.username);
				$("#user-data").show();
				
				var usuarioPerfil = $("#usuario-perfil");
				var templatePerfil = Perfil.perfilTemplate;
				templatePerfil = templatePerfil.replace(/%USERNAME%/g, user.username);
				templatePerfil = templatePerfil.replace(Perfil.NOMEUSUARIO, user.nome);
				
				usuarioPerfil.append(templatePerfil);
				
				if (Chorus.callbackFunction != undefined) {
					Chorus.callbackFunction();
				}
				
				Chorus.loadLoggedUserPhoto();
				
			} else {
				$("#login-form").show();
				$("#loginUsername").focus();
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};

Chorus.loadLoggedUserPhoto = function() {
	$.ajax({
		url: '../usuario/userPhoto',
		method: 'POST',
		success: function(data) {
			var perfilAvatar = $(".span-avatar-perfil");
			perfilAvatar.append('<img class="img-polaroid" title="Perfil"  src="'+ data.string +'" />');
		}
	});
};

Chorus.follow = function(username, callbackFunction) {
	$.ajax({
		url: '../usuario/seguir',
		method: 'POST',
		data: "username=" + username,
		success: function(data) {
			if (callbackFunction !== undefined) {
				callbackFunction();
			}
		}
	});
};

Chorus.unfollow = function(username, callbackFunction) {
	$.ajax({
		url: '../usuario/deixarSeguir',
		method: 'POST',
		data: "username=" + username,
		success: function(data) {
			if (callbackFunction !== undefined) {
				callbackFunction();
			}
		}
	});
};

Perfil.NOMEUSUARIO = '%NOME_USUARIO%';
Perfil.USERNAME = '%USERNAME%';

Perfil.perfilTemplate = '<span class="span-avatar-perfil"></span>' +
	    				'<span class="perfil-nome span2" style="width: 100%;">' 
							+	Perfil.NOMEUSUARIO + 
						'</span>' +
	    				'<span class="perfil-username">'
	    					+ '<a href="../usuario/'+Perfil.USERNAME+'" class="span-username">@'+Perfil.USERNAME+'</a>' +
	    				'</span>' +
	    				'<div class="clearfix"></div>';



$(document).ready(function() {
	$("#login-form").hide();
	$("#user-data").hide();
	
	Chorus.verifyUserLogin();
});