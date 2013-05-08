$(document).ready(function () {
	$("#btn-chorar").click(function() {
		Timeline.chorar();
	});
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
			console.log(data);
		},
		error: function(err) {
			console.log(err);
		}
	});
};
