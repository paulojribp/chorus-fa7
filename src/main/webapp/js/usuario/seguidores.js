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
				template = template.replace(Seguidores.USERNAME, usuarioDto.username);
				template = template.replace(Seguidores.NOMEUSUARIO, usuarioDto.nome);
				template = template.replace(Seguidores.SEGUIDO, usuarioDto.seguido);
				template = template.replace(Seguidores.SEGUINDO, usuarioDto.seguindo);
				
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
Seguidores.SEGUINDO = '%SEGUINDO%';

Seguidores.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar"><img alt="perfil" src="'+ Seguidores.AVATAR +'" class="avatar" /></span>' +
			    				'<span class="span-nome">' +
			    					Seguidores.NOMEUSUARIO +
				    				'<a class="span-username">'+Seguidores.USERNAME+'</a>' +
			    				'</span>' +
			    				'<span class="span1">' +
			    					Seguidores.SEGUIDO +
			    				'</span>' +
			    				'<span class="span9">'+Seguidores.SEGUIDOR+'</span>' +
			    			'</div>';