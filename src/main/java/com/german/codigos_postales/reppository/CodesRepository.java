package com.german.codigos_postales.reppository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.german.codigos_postales.entity.ZipCodes;

public interface CodesRepository extends JpaRepository<ZipCodes,String> {

	    public List<ZipCodes> findByCodigoPostal(String idCodigoPostal);
	    public List<ZipCodes> findByAsentamiento(String asentamiento);
}

