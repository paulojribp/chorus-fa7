var Chorus = {};
var Perfil = {};

Chorus.verifyUserLogin = function() {
	$.ajax({
		url: '../usuario/loggedUser',
		method: 'POST',
		success: function(data) {
			var user = data.usuarioDto;
			
			if (user.username !== undefined) {
				$("#logged-username").text(user.nome);
				$("#user-data").show();
				
				var usuarioPerfil = $("#usuario-perfil");
				var templatePerfil = Perfil.perfilTemplate;
				templatePerfil = templatePerfil.replace(Perfil.AVATAR, user.gravatarUrl);
				templatePerfil = templatePerfil.replace(Perfil.USERNAME, user.username);
				templatePerfil = templatePerfil.replace(Perfil.NOMEUSUARIO, user.nome);
				
				usuarioPerfil.append(templatePerfil);
				
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

Perfil.NOMEUSUARIO = '%NOME_USUARIO%';
Perfil.USERNAME = '%USERNAME%';
Perfil.AVATAR = '%AVATAR%';

Perfil.perfilTemplate = '<div class="span12"> ' +
			    				'<span class="span-avatar-perfil"><img alt="perfil" src="'+ Perfil.AVATAR +'" /></span>' +
			    				'<span class="span8">' +
			    				Perfil.NOMEUSUARIO +
			    				'<a class="span-username">'+Perfil.USERNAME+'</a>' +
			    				'</span>' +
			    		'</div>';



$(document).ready(function() {
	$("#login-form").hide();
	$("#user-data").hide();
	
	Chorus.verifyUserLogin();
});