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
				template = template.replace(Seguidores.USERNAME, usuarioDto.username);
				template = template.replace(Seguidores.NOMEUSUARIO, usuarioDto.nome);
				template = template.replace(Seguidores.SEGUIDO, usuarioDto.seguido);
				if(usuarioDto.seguindo){
					template = template.replace(Seguidores.BUTTON_STATUS, '<button id="btn-deixar-seguir"  onclick="Usuario.deixarSeguir();" type="submit" class="btn btn-danger btn-seguir" >Deixar de Seguir</button>');
				}else{
					template = template.replace(Seguidores.BUTTON_STATUS, '<button id="btn-seguir" type="submit" class="btn btn-danger btn-seguir" >Seguir</button>');
				}
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
Seguidores.BUTTON_STATUS = '%BUTTON_STATUS%';

Seguidores.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar"><img alt="perfil" src="'+ Seguidores.AVATAR +'" class="avatar" /></span>' +
			    				'<span class="span-nome">' +
			    					Seguidores.NOMEUSUARIO +
				    				'<a class="span-username">'+Seguidores.USERNAME+'</a>' +
			    				'</span>' +
			    				'<span class="span3">' +
			    					Seguidores.BUTTON_STATUS
			    				'</span>' +
			    			'</div>';
