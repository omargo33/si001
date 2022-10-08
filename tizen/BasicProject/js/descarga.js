function validarDescarga() {
	var download_api_capability = tizen.systeminfo
			.getCapability("http://tizen.org/feature/download");
	if (download_api_capability === false) {
		document.getElementById('mensaje').innerHTML = "Download API is not supported on this device.";
		return;
	} else {
		document.getElementById('mensaje').innerHTML = "Descarga permitida";
	}

	var listener = {
		onprogress : function(id, receivedSize, totalSize) {			
			document.getElementById('mensaje').innerHTML = 'Received with id: '+ id + ', ' + receivedSize + '/' + totalSize;
		},
		onpaused : function(id) {
			
			document.getElementById('mensaje').innerHTML = 'Paused with id: '+ id;
		},
		oncanceled : function(id) {
			
			document.getElementById('mensaje').innerHTML = 'Canceled with id: '+ id;
		},
		oncompleted : function(id, path) {
			
			document.getElementById('mensaje').innerHTML = 'Completed with id: '+ id + ', path: ' + path;
		},
		onfailed : function(id, error) {
			
			document.getElementById('mensaje').innerHTML = 'Failed with id: '+ id + ', error name: ' + error.name;
		}
	};

	// Starts downloading the file from the Web with the corresponding
	// callbacks.
	//var downloadRequest = new tizen.DownloadRequest(
	//		"http://download.tizen.org/tools/README.txt", "removable_sda");
	//var downloadId = tizen.download.start(downloadRequest, listener);

	
	// If you want to download a large file through Wi-Fi,
	var wifi_capability = tizen.systeminfo
			.getCapability("http://tizen.org/feature/network.wifi");
	var wifiDownloadRequest = new tizen.DownloadRequest(
			"http://download.tizen.org/tools/archive/14.02.2/Ubuntu_12.04/qemu_1.6.0rc3.orig.tar.gz",
			"/opt/media/USBDriveA", null, "WIFI");

	if (wifi_capability === true) {
		tizen.download.start(wifiDownloadRequest, listener);
	} else {
		// If you call tizen.download.start(), NotSupportedError will be thrown.
		document.getElementById('mensaje').innerHTML = "This device doesn't support Download API through Wi-Fi.";
	}

}

/**
 * Lanzado al inicio.
 */
setTimeout(validarDescarga, 3000);
