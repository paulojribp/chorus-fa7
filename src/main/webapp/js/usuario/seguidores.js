var Usuario = {};
$(document).ready(function () {
	Seguidores.listarSeguidores();
});

var Seguidores = {};
Seguidores.listarSeguidores = function() {
	$.ajax({
		url: '../usuario/listarSeguidores',
		method: 'POST',
		success: function(data) {
			var seguidoresList = $("#chorinhos-list");
			for (var x = 0, size = data.list.length; x < size; x++) {
				var usuarioDto = data.list[x];
				var template = Seguidores.chorusTemplate;
				template = template.replace(Seguidores.AVATAR, usuarioDto.gravatarUrl);
				template = template.replace(/%USERNAME%/g, usuarioDto.username);
				template = template.replace(Seguidores.NOMEUSUARIO, usuarioDto.nome);
				
				seguidoresList.append(template);
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};

Seguidores.NOMEUSUARIO = '%NOME_USUARIO%';
Seguidores.USERNAME = '%USERNAME%';
Seguidores.AVATAR = '%AVATAR%';
Seguidores.SEGUIDO = '%SEGUIDO%';
Seguidores.BUTTON_STATUS = '%BUTTON_STATUS%';

Seguidores.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar"><img alt="perfil" src="'+ Seguidores.AVATAR +'" class="avatar" /></span>' +
			    				'<span class="span-nome">' +
			    					Seguidores.NOMEUSUARIO +
				    				'<a href="../usuario/'+Seguidores.USERNAME+'" class="span-username">@'+Seguidores.USERNAME+'</a>' +
			    				'</span>' +
			    			'</div>';
