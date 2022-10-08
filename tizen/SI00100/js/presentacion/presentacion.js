/**
 * Script para leer archivo de datos de presentacion
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-25
 * 
 */

var documentsDir;
/**
 * Funcion obtener la unidad externa.
 */
function buscarExternal(storages) {
	for (var i = 0; i < storages.length; i++) {
		if (storages[i].type === "EXTERNAL") {
			buscarArchivoConfig(storages[i].label);
		}
	}
}

/**
 * Metodo para conocer el paht real del sistema.
 * 
 * @param nombreVirtualDir
 */
function buscarArchivoConfig(nombreVirtualDir) {
	tizen.filesystem.resolve(nombreVirtualDir, function(dir) {
		documentsDir = dir;
		//pathConfig = documentsDir.fullPath + "/config/orden.json";
		//alert("file a procesar " + pathConfig );
		leer(documentsDir);
	}, function(error) {
		alert("file no encontrado");
	}, "rw");
}

function leer(dir) {
	var file = dir.resolve("config/orden.json");
	file.openStream("r", function(fs) {
		text = fs.read(file.fileSize);		
		fs.close();
		obj = JSON.parse(text);
		for (var i = 0; i < obj.productos.length; i++) {
			//alert(obj.productos[i].indice);
		}
	}, function(e) {
		console.log("Error " + e.message);
	}, "UTF-8");
}

/**
 * Funcion para ejecutar el listado de storage
 */
function ejecutarFileSystem() {
	try {
		tizen.filesystem.listStorages(buscarExternal);
	} catch (error) {

	}
}

/**
 * Lanzado al inicio.
 */
setTimeout(ejecutarFileSystem, 1);
