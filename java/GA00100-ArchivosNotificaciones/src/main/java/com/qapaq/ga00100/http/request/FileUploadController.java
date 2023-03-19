package com.qapaq.ga00100.http.request;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qapaq.ga00100.utility.FileUploadUtil;

import lombok.Data;

/**
 * Controlador para cargar archivos.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 * @see "https://www.codejava.net/frameworks/spring-boot/file-download-upload-rest-api-examples"
 * 
 */
@RestController
public class FileUploadController {

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/ga_001_10/downloadFile/" + filecode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Clase para la respuesta de la carga de archivos.
     */
    @Data
    public class FileUploadResponse {
        private String fileName;
        private String downloadUri;
        private long size;
    }
}