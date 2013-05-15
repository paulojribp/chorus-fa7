$(document).ready(function () {
	Seguindo.listarSeguindo();
});

var Seguindo = {};
Seguindo.listarSeguindo = function() {
	$.ajax({
		url: '../usuario/listarSeguindo',
		method: 'POST',
		success: function(data) {
			var seguindoList = $("#chorinhos-list");
			for (var x = 0, size = data.list.length; x < size; x++) {
				var usuarioDto = data.list[x];
				var template = Seguindo.chorusTemplate;
				template = template.replace(Seguindo.AVATAR, usuarioDto.gravatarUrl);
				template = template.replace(Seguindo.USERNAME, usuarioDto.username);
				template = template.replace(Seguindo.NOMEUSUARIO, usuarioDto.nome);
				template = template.replace(Seguindo.SEGUIDO, usuarioDto.seguido);
				template = template.replace(Seguindo.SEGUINDO, usuarioDto.seguindo);
				
				seguindoList.append(template);
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};

Seguindo.NOMEUSUARIO = '%NOME_USUARIO%';
Seguindo.USERNAME = '%USERNAME%';
Seguindo.AVATAR = '%AVATAR%';
Seguindo.SEGUIDO = '%SEGUIDO%';
Seguindo.SEGUINDO = '%SEGUINDO%';

Seguindo.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar"><img alt="perfil" src="'+ Seguindo.AVATAR +'" class="avatar" /></span>' +
			    				'<span class="span-nome">' +
			    					Seguindo.NOMEUSUARIO +
				    				'<a class="span-username">'+Seguindo.USERNAME+'</a>' +
			    				'</span>' +
			    				'<span class="span1">' +
			    					Seguindo.SEGUIDO +
			    				'</span>' +
			    				'<span class="span9">'+Seguindo.SEGUINDO+'</span>' +
			    			'</div>';