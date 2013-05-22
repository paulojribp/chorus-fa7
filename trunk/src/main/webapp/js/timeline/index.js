$(document).ready(function () {
	$("#btn-chorar").click(function() {
		Timeline.chorar();
	});
	
	$("#chorinho-length").val(Timeline.msgSize);
	var escreverTxtarea = $("#escrever-chorinho");
	escreverTxtarea.keyup(function(key) {
		var size = escreverTxtarea.val().length;
		$("#chorinho-length").text(Timeline.msgSize - size);
		if (size > Timeline.msgSize) {
			$("#chorinho-length").addClass('txtred');
		} else {
			$("#chorinho-length").removeClass('txtred');
		}
	});
	
	Timeline.listarChorus();
});

var Timeline = {};
Timeline.msgSize = 144;
Timeline.chorar = function() {
	var choru = $("#escrever-chorinho").val();
	
	if (choru.length <= Timeline.msgSize) {
		$.ajax({
			url: '../timeline/publicar',
			method: 'POST',
			data: {"chorus.mensagem" : choru},
			success: function(data) {
				$("#escrever-chorinho-msg").removeClass("alert-error").addClass("alert-info");
				$("#escrever-chorinho").val('');
				$("#escrever-chorinho-msg").text('Você chorou com sucesso.');
				$("#escrever-chorinho-msg").fadeIn('slow');
				Timeline.listarChorus();
				setTimeout(function() {
					$("#escrever-chorinho-msg").fadeOut('slow');
				}, 3000);
			},
			error: function(err) {
				console.log(err);
			}
		});
	} else {
		$("#escrever-chorinho-msg").removeClass("alert-info").addClass("alert-error");
		$("#escrever-chorinho-msg").text('Seu choru possui mais de 144 caracteres');
		$("#escrever-chorinho-msg").fadeIn('slow');
	}
	
};
	
Timeline.avatarMails = [];
Timeline.ultimoChoru = '';

Timeline.listarChorus = function() {
	$.ajax({
		url: '../timeline/listar',
		method: 'GET',
		success: function(data) {
			
			Timeline.refresh(data.list);
		},
		error: function(err) {
			console.log(err);
		}
	});
};

Timeline.refresh = function(lista) {
	var chorinhosList = $("#chorinhos-list");
	$(".span12.chorus").remove();
	
	for (var x = 0, size = lista.length; x < size; x++) {
		var chorus = lista[x];
		var template = Timeline.chorusTemplate;
		template = template.replace(Timeline.AVATAR, chorus.gravatarUrl);
		template = template.replace(/%USERNAME%/g, chorus.username);
		template = template.replace(Timeline.NOMEUSUARIO, chorus.nome);
		template = template.replace(Timeline.CHORUSTIME, chorus.datahora);
		template = template.replace(Timeline.CHORUSMENSAGEM, chorus.mensagem);
		
		chorinhosList.append(template);
		if (!Timeline.isUsermailAdded(chorus.username, chorus.email)) {
			var userData = {};
			userData.username = chorus.username;
			userData.email = chorus.email;
			Timeline.avatarMails[Timeline.avatarMails.length++] = userData;
		}
	}
	
	for (var y=0, sizeY = Timeline.avatarMails.length; y < sizeY; y++) {
		Timeline.loadUserPhoto(Timeline.avatarMails[y]);
	}
};

Timeline.isUsermailAdded = function(username, email) {
	for (var y=0, sizeY = Timeline.avatarMails.length; y < sizeY; y++) {
		if (Timeline.avatarMails[y].username === username) {
			return true;
		}
	}
	
	return false;
};

Timeline.loadUserPhoto = function(userData) {
	$.ajax({
		url: '../usuario/userPhoto',
		method: 'POST',
		data: "email=" + userData.email,
		success: function(data) {
			var perfilAvatar = $(".span-avatar."+userData.username);
			for (var x=0,size=perfilAvatar.length; x<size; x++) {
				$(perfilAvatar[x]).append('<img alt="perfil"  src="'+ data.string +'" />');
			}
		}
	});
};

Timeline.NOMEUSUARIO = '%NOME_USUARIO%';
Timeline.USERNAME = '%USERNAME%';
Timeline.CHORUSTIME = '%CHORUS_TIME%';
Timeline.CHORUSMENSAGEM = '%CHORUS_MENSAGEM%';
Timeline.AVATAR = '%AVATAR%';

Timeline.chorusTemplate = 
							'<li id="chorinhos" class="media">' +
								'<a class="pull-left" href="#">' +
								  '<img class="media-object img-polaroid" src="'+ Timeline.USERNAME +'">' +
								'</a>' +
								'<div class="media-body">' +
								'<div class="row-fluid">' +
				    				'<span class="nome">' +
				    					Timeline.NOMEUSUARIO +
				    				'</span>' +
				    				'<span class="username">' +
				    					'<a href="../usuario/'+Timeline.USERNAME+'" class="span-username">@'+Timeline.USERNAME+'</a>' +
			    					'</span>' +
				    				'<span class="tempo">' +
				    					Timeline.CHORUSTIME +
				    				'</span>' +
			    				'</div>' +
				    				'<span>'+Timeline.CHORUSMENSAGEM+'</span>' +
								'</div>' +
							'</li>';
	