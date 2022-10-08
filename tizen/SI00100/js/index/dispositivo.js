/**
 * Script para conocer informacion del dispositivo.
 * 
 * Dispositivo
 * 
 * @author o.velez@qapaq.io
 * @date 2022-08-25
 * 
 */

/**
 * Funcion del dispositivo.
 */
function ejecutarDispositivo() {
	tizen.systeminfo.getPropertyValue("DISPLAY",
		function(display) {
			var jsonDisplay = JSON.parse(JSON.stringify(display));
			
			document.getElementById('ancho').innerHTML = jsonDisplay.resolutionWidth + "px";
			document.getElementById('largo').innerHTML = jsonDisplay.resolutionHeight + "px";
			
			if (parseFloat(jsonDisplay.resolutionWidth) > parseFloat(jsonDisplay.resolutionHeight)) {
				document.getElementById('orientacion').innerHTML = "Horizontal";
			} else {
				document.getElementById('orientacion').innerHTML = "Vertical";
			}
		},
		
		function(error) {
			document.getElementById('notaDispositivo').innerHTML = "Error Wifi: "+ error;
			}
		);
}

/**
 * Lanzado al inicio.
 */
setTimeout(ejecutarDispositivo, 500);
