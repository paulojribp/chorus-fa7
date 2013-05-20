$(document).ready(function () {
	UsuarioPerfil.listarChorus();
});

var UsuarioPerfil = {};

UsuarioPerfil.listarChorus = function() {
	var idx = window.location.pathname.lastIndexOf("/");
	var username = window.location.pathname.substr(idx+1);
	$.ajax({
		url: '../timeline/listar/' + username,
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

UsuarioPerfil.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar '+UsuarioPerfil.USERNAME+'"><img alt="perfil"  src="'+UsuarioPerfil.AVATAR+'" /></span>' +
			    				'<span class="span-nome">' +
			    					UsuarioPerfil.NOMEUSUARIO +
				    				'<a href="'+UsuarioPerfil.USERNAME+'" class="span-username">@'+UsuarioPerfil.USERNAME+'</a>' +
			    				'</span>' +
			    				'<span class="span1">' +
			    					UsuarioPerfil.CHORUSTIME +
			    				'</span>' +
			    				'<span class="span9">'+UsuarioPerfil.CHORUSMENSAGEM+'</span>' +
			    			'</div>';

