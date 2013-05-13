$(document).ready(function () {
	$("#btn-chorar").click(function() {
		Timeline.chorar();
	});
	
	Timeline.listarChorus();
});

var Timeline = {};
Timeline.chorar = function() {
	var choru = $("#escrever-chorinho").val();
	
	$.ajax({
		url: '../timeline/publicar',
		method: 'POST',
		data: {"chorus.mensagem" : choru},
		success: function(data) {
			$("#escrever-chorinho-msg").text('Você chorou com sucesso.');
			$("#escrever-chorinho-msg").show();
		},
		error: function(err) {
			console.log(err);
		}
	});
	
};
	
Timeline.listarChorus = function() {
	$.ajax({
		url: '../timeline/listar',
		method: 'GET',
		success: function(data) {
			var chorinhosList = $("#chorinhos-list");
			for (var x = 0, size = data.list.length; x < size; x++) {
				var chorus = data.list[x];
				var template = Timeline.chorusTemplate;
				template = template.replace(Timeline.AVATAR, chorus.gravatarUrl);
				template = template.replace(Timeline.USERNAME, chorus.username);
				template = template.replace(Timeline.NOMEUSUARIO, chorus.nome);
				template = template.replace(Timeline.CHORUSTIME, datahora.$);
				template = template.replace(Timeline.CHORUSMENSAGEM, chorus.mensagem);
				
				chorinhosList.append(template);
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};

Timeline.NOMEUSUARIO = '%NOME_USUARIO%';
Timeline.USERNAME = '%USERNAME%';
Timeline.CHORUSTIME = '%CHORUS_TIME%';
Timeline.CHORUSMENSAGEM = '%CHORUS_MENSAGEM%';
Timeline.AVATAR = '%AVATAR%';

Timeline.chorusTemplate = '<div class="span12 chorus"> ' +
			    				'<span class="span-avatar"><img alt="perfil" src="'+ Timeline.AVATAR +'" class="avatar" /></span>' +
			    				'<span class="span-nome">' +
			    					Timeline.NOMEUSUARIO +
				    				'<a class="span-username">'+Timeline.USERNAME+'</a>' +
			    				'</span>' +
			    				'<span class="span1">' +
			    					Timeline.CHORUSTIME +
			    				'</span>' +
			    				'<span class="span9">'+Timeline.CHORUSMENSAGEM+'</span>' +
			    			'</div>';

