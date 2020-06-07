package com.german.codigos_postales.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.german.codigos_postales.entity.EmptyJsonResponse;
import com.german.codigos_postales.entity.ResponseCodigoPostalDto;
import com.german.codigos_postales.entity.ZipCodes;
import com.german.codigos_postales.service.CodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/zip-codes")
public class ZipCoidesController {
	
	@Autowired
	private CodeService codeService;
	
	
	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<ResponseCodigoPostalDto> getByIdCp(@PathVariable("id") String id) {
	        log.info("Fetching CP with id {}", id);

	        ResponseCodigoPostalDto response = new ResponseCodigoPostalDto();
	        response = codeService.findByCodigoPostal(id);
	        
	        if (response != null) {
	        	return  ResponseEntity.ok(response);
	        }else {
//	    		throw new ModeloNotFoundException("Codigo postal no encontrado: " + id);
		        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
	        }
	  }
	  
	  
	  @GetMapping(value = "/asentamiento/{asentamiento}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<ZipCodes>> getByAsentamiento(@PathVariable("asentamiento") String asentamiento) {
	        log.info("Fetching CP with id {}", asentamiento);

	        List<ZipCodes> response = new ArrayList<>();
	        response = codeService.findByAsentamiento(asentamiento);
	        
	        if (response != null) {
	        	return  ResponseEntity.ok(response);
	        }
	        
	        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
	    }
	  

}
