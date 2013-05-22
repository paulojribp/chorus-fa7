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
				template = template.replace(/%USERNAME%/g, usuarioDto.username);
				template = template.replace(Seguindo.NOMEUSUARIO, usuarioDto.nome);
				
				seguindoList.append(template);
				if (usuarioDto.seguindo) {
					$("#btn-follow-"+usuarioDto.username).hide();
					$("#btn-unfollow-"+usuarioDto.username).show();
				} else {
					$("#btn-follow-"+usuarioDto.username).show();
					$("#btn-unfollow-"+usuarioDto.username).hide();
				}
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

Seguindo.chorusTemplate = '<li id="chorinhos-'+Seguindo.USERNAME+'" class="media">' +
							'<a class="pull-left" href="#">' +
							  '<img class="media-object img-polaroid" src="../images/defaultuser.png">' +
							'</a>' +
							'<div class="media-body">' +
							'<div class="row-fluid">' +
								'<span class="nome">' +
									Seguindo.NOMEUSUARIO +
								'</span>' +
								'<span class="username">' +
									'<a href="../usuario/'+Seguindo.USERNAME+'" class="span-username">@'+Seguindo.USERNAME+'</a>' +
								'</span>' +
							'</div>' +
								'<span class="pull-right">'+
									'<button id="btn-follow-'+Seguindo.USERNAME+'" '+
										'onclick="Chorus.follow(\''+Seguindo.USERNAME+'\',function() {$(\'#btn-follow-'+Seguindo.USERNAME+'\').hide(); $(\'#btn-unfollow-'+Seguindo.USERNAME+'\').show()})" '+
										'class="btn btn-success hidden">Seguir</button>' +
									'<button id="btn-unfollow-'+Seguindo.USERNAME+'" '+
										'onclick="Chorus.unfollow(\''+Seguindo.USERNAME+'\',function() {$(\'#btn-unfollow-'+Seguindo.USERNAME+'\').hide(); $(\'#btn-follow-'+Seguindo.USERNAME+'\').show()})" '+
										'class="btn btn-danger hidden">Deixar de Seguir</button>' +
				    			'</span>' +
							'</div>' +
						  '</li>';
