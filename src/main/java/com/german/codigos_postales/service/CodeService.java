package com.german.codigos_postales.service;

import java.util.List;

import com.german.codigos_postales.entity.ResponseCodigoPostalDto;
import com.german.codigos_postales.entity.ZipCodes;

public interface CodeService {
	
    public ResponseCodigoPostalDto findByCodigoPostal(String codigoPostal);
    public List<ZipCodes> findByAsentamiento(String asentamiento);


}
