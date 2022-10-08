/**
 * Objeto para actualizar reloj con formato y de forma periodica.
 * 
 */
var tiempoRefresco = (10 * 1000);
/**
 * Funcion para actualizarReloj
 */
function refrescarReloj() {
	var today = new Date();
	var hora="0"+ today.getHours();
	var minuto="0"+ today.getMinutes();	
	document.getElementById('reloj').innerHTML =  hora.substring(hora.length-2, hora.length) +":"+minuto.substring(minuto.length-2, minuto.length);	
	setTimeout(refrescarReloj, tiempoRefresco);
}

/**
 * Lanzado al inicio.
 */
setTimeout(refrescarReloj, tiempoRefresco);
