function eliminar(id) {
	console.log("modal");
	var modal = document.getElementById("containerModalId");
	var windowClose = document.getElementById("close");
	document.getElementById("messageId").innerHTML = "¿Estas seguro de eliminar esta Peticion de la lista: " + id + "?";
	modal.style.display = "block";
	document.getElementById("formDelete").setAttribute("action", "/peticion/eliminar/" + id);
	if (windowClose) {
		windowClose.onclick = function() {
			modal.style.display = "none";
		}
	}
}