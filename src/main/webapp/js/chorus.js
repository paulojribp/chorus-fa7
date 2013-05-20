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
				templatePerfil = templatePerfil.replace(/%USERNAME%/g, user.username);
				templatePerfil = templatePerfil.replace(Perfil.NOMEUSUARIO, user.nome);
				
				usuarioPerfil.append(templatePerfil);
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
			perfilAvatar.append('<img id="perfil-avatar" alt="perfil"  src="'+ data.string +'" />');
		}
	});
};

Perfil.NOMEUSUARIO = '%NOME_USUARIO%';
Perfil.USERNAME = '%USERNAME%';

Perfil.perfilTemplate = '<div class="span12"> ' +
			    				'<span class="span-avatar-perfil"></span>' +
			    				'<span class="span8">' +
			    				Perfil.NOMEUSUARIO +
			    				'<a href="../usuario/'+Perfil.USERNAME+'" class="span-username">@'+Perfil.USERNAME+'</a>' +
			    				'</span>' +
			    		'</div>';



$(document).ready(function() {
	$("#login-form").hide();
	$("#user-data").hide();
	
	Chorus.verifyUserLogin();
});