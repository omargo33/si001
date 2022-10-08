/**
 * Script para conocer informacion del sistema de archivos.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-08-25
 * 
 */

var documentsDir;

/**
 * Funcion obtener la unidad externa.
 */
function buscarExternal(storages) {
	var estado = false;
	for (var i = 0; i < storages.length; i++) {
		if (storages[i].type === "EXTERNAL") {
			buscarPath(storages[i].label);			
			document.getElementById('usb').innerHTML = storages[i].label;
			document.getElementById('estado').innerHTML = storages[i].state;
			estado = true;
		}
	}
	if(!estado){
		document.getElementById('nota').innerHTML = "Error, no se detecta Disco Externo";
	}
}

/**
 * Metodo para conocer el paht real del sistema.
 * 
 * @param nombreVirtualDir
 */
function buscarPath(nombreVirtualDir) {
	tizen.filesystem.resolve(nombreVirtualDir, function(dir) {
		documentsDir = dir;
		document.getElementById('path').innerHTML = documentsDir.fullPath;
		calculosArchivos();
	}, function(error) {
		document.getElementById('nota').innerHTML = "Error Path " + error.message + " " + nombreVirtualDir;
	}, "rw");
}

/**
 * Funcion para calcular los archivos.
 * 
 * @param files
 */
function calculosArchivos(){	
	tizen.systeminfo.getPropertyValue("STORAGE",
			function(storage) {
				var jsonStorage = JSON.parse(JSON.stringify(storage));
				
				for(var i=0; i < jsonStorage.units.length; i++){
					if(jsonStorage.units[1].type==='USB_DEVICE'){
						document.getElementById('capacidad').innerHTML = (jsonStorage.units[i].capacity/1024/1024/1024).toFixed(2) + "GB";
						document.getElementById('libre').innerHTML = (jsonStorage.units[i].availableCapacity/1024/1024/1024).toFixed(2) + "GB";
					}
				}
			},
			
			function(error) {
				document.getElementById('nota').innerHTML = "Error Wifi: "+ error;
				}
			);
}


/**
 * Funcion para ejecutar el listado de storage
 */
function ejecutarFileSystem() {
	try {
		tizen.filesystem.listStorages(buscarExternal);
	} catch (error) {
		document.getElementById('nota').innerHTML = "Error Obtención " + error.message;
	}
}

/**
 * Lanzado al inicio.
 */
setTimeout(ejecutarFileSystem, 500);
