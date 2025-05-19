function realizarBusqueda() {
			    let query = document.getElementById("buscar").value;
			    if (query.length > 2) {
			        fetch(`/user/buscar`, {
			            method: "POST",
			            headers: { "Content-Type": "application/x-www-form-urlencoded" },
			            body: new URLSearchParams({ buscar: query })
			        })
			        .then(response => response.text())
			        .then(html => {
			            document.querySelector("tbody").innerHTML = new DOMParser()
			                .parseFromString(html, "text/html").querySelector("tbody").innerHTML;
			        })
			        .catch(error => console.error("Error en la búsqueda:", error));
			    }
			}