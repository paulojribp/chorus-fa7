$(document).ready(function () {
	$("#btn-cadastrar").click(function() {
		Usuario.newUser();
	});
	
	$("#cadastro-msg").hide();
});

var Usuario = {};
Usuario.newUser = function() {
	$("#cadastro-msg").hide();
	$("#cadastro-msg").removeClass('alert-success');
	$("#cadastro-msg").removeClass('alert-error');
	
	var usuario = {
		"usuario.username": $('#username').val(),
		"usuario.nome": $('#nome').val(),
		"usuario.email": $('#email').val(),
		"usuario.senha": $('#senha').val(),
		"usuario.confirmaSenha": $('#confirmasenha').val()
	};
	
	$.ajax({
		url: '../usuario/salvar',
		method: 'POST',
		data: usuario,
		success: function(data) {
			console.log(data);
			if (data.returnDto.success === true) {
				$("#cadastro-msg").addClass('alert-success');
				$("#cadastro-msg").text("Cadastro realizado com sucesso. Realize o login");
				$("#cadastro-msg").show();
				Usuario.clearFormCadastro();
			} else {
				$("#cadastro-msg").addClass('alert-error');
				$("#cadastro-msg").text(data.returnDto.message);
				$("#cadastro-msg").show();
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
	
};

Usuario.clearFormCadastro = function() {
	$('#username').val('');
	$('#nome').val('');
	$('#email').val('');
	$('#senha').val('');
	$('#confirmasenha').val('');
};