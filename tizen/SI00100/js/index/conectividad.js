/**
 * Script para conocer informacion de la conexion a red.
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2022-08-25
 * 
 */

/**
 * Metodo para informar el estado de la red WIFI.
 * 
 */
function getWifi() {
	tizen.systeminfo.getPropertyValue("WIFI_NETWORK", function(net) {
		var jsonNet = JSON.parse(JSON.stringify(net));
		if (jsonNet.status === 'ON') {
			document.getElementById('ipWifi').innerHTML = jsonNet.ipAddress;
			document.getElementById('macWifi').innerHTML = jsonNet.macAddress;
			document.getElementById('ssid').innerHTML = jsonNet.ssid + " " +
					(parseFloat(jsonNet.signalStrength) * 100).toFixed(0) +
					 "%";
		}
	}, function(error) {
		document.getElementById('notaConect').innerHTML = "Error Wifi: " + error;
	});
}

/**
 * Metodo para informar el estado de la red cableada.
 * 
 */
function getNetwork() {
	tizen.systeminfo.getPropertyValue("ETHERNET_NETWORK", function(net) {
		var jsonNet = JSON.parse(JSON.stringify(net));

		if (jsonNet.status === 'CONNECTED') {
			document.getElementById('ipNet').innerHTML = jsonNet.ipAddress;
			document.getElementById('macNet').innerHTML = jsonNet.macAddress;
			document.getElementById('estadoNet').innerHTML = jsonNet.status;
		}
	}, function(error) {
		document.getElementById('notaConect').innerHTML = "Error network: " + error;
	});

}

/**
 * Funcion para ejecuatar datos del sistema.
 * 
 */
function ejecutarNetwork() {
	getWifi();
	getNetwork();
}

/**
 * Lanzado al inicio.
 */
setTimeout(ejecutarNetwork, 400);
