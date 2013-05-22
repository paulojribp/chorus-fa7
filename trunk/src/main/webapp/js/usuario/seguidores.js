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
				var btnSeguir = $("#btn-follow-"+usuarioDto.username);
				var btnDeixar = $("#btn-unfollow-"+usuarioDto.username);
				
				if (usuarioDto.seguido) {
					btnSeguir.hide();
					btnDeixar.show();
				} else {
					btnSeguir.show();
					btnDeixar.hide();
				}
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

Seguidores.chorusTemplate = '<li id="chorinhos-'+Seguidores.USERNAME+'" class="media">' +
							'<a class="pull-left" href="#">' +
							'<img class="media-object img-polaroid" src="../images/defaultuser.png">' +
							'</a>' +
							'<div class="media-body">' +
							'<div class="row-fluid">' +
								'<span class="nome">' +
									Seguidores.NOMEUSUARIO +
								'</span>' +
								'<span class="username">' +
									'<a href="../usuario/'+Seguidores.USERNAME+'" class="span-username">@'+Seguidores.USERNAME+'</a>' +
								'</span>' +
							'</div>' +
								'<span class="pull-right">'+
									'<button id="btn-follow-'+Seguidores.USERNAME+'" '+
										'onclick="Chorus.follow(\''+Seguidores.USERNAME+'\',function() {$(\'#btn-follow-'+Seguidores.USERNAME+'\').hide(); $(\'#btn-unfollow-'+Seguidores.USERNAME+'\').show()})" '+
										'class="btn btn-success hidden">Seguir</button>' +
									'<button id="btn-unfollow-'+Seguidores.USERNAME+'" '+
										'onclick="Chorus.unfollow(\''+Seguidores.USERNAME+'\',function() {$(\'#btn-unfollow-'+Seguidores.USERNAME+'\').hide(); $(\'#btn-follow-'+Seguidores.USERNAME+'\').show()})" '+
										'class="btn btn-danger hidden">Deixar de Seguir</button>' +
								'</span>' +
							'</div>' +
							'</li>';
