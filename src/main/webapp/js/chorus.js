var Chorus = {};
Chorus.verifyUserLogin = function() {
	$.ajax({
		url: '../usuario/loggedUser',
		method: 'POST',
		success: function(data) {
			var user = data.usuarioDto;
			
			if (user.username !== undefined) {
				$("#logged-username").text(user.nome);
				$("#user-data").show();
			} else {
				$("#login-form").show();
				$("#loginUsername").focus();
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
};



$(document).ready(function() {
	$("#login-form").hide();
	$("#user-data").hide();
	
	Chorus.verifyUserLogin();
});