package com.german.codigos_postales.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.german.codigos_postales.entity.AsentamientoDto;
import com.german.codigos_postales.entity.ResponseCodigoPostalDto;
import com.german.codigos_postales.entity.ZipCodes;
import com.german.codigos_postales.reppository.CodesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CodeServicesImpl implements CodeService {

//	@Value("${spring.springdata.zip_codes.busqueda_por_cp}")
	private static String URI_BY_CP = "https://micodigopostal.org/buscarcp.php?buscar=";

//	@Value("${spring.springdata.zip_codes.busqueda_por_asentamienti}")
	private static String URI_BY_ASENTAMIENTO = "https://micodigopostal.org/buscar.php?buscar=";

	@Autowired
	private CodesRepository codesRepository;

	@Override
	public ResponseCodigoPostalDto findByCodigoPostal(String codigoPostal) {
		ResponseCodigoPostalDto responseCodigoPostalDto = new ResponseCodigoPostalDto();
		List<ZipCodes> resultList = new ArrayList<>();
		ZipCodes zipCodes;
		Element dataTablesearch;
		Elements trs, tds;

		resultList = codesRepository.findByCodigoPostal(codigoPostal);

		if (!resultList.isEmpty()) {
			log.info("[findByCodigoPostal] Ya existe en la base de datos, se regresa valor recuperado");
			responseCodigoPostalDto = getResponseCPFromData(resultList);
		} else {
			log.info(
					"[findByCodigoPostal] No se encontro el código en la base de datos, se procede a recuperarlo por Web Scraping ");
			Document doc;
			try {
				doc = Jsoup.connect(URI_BY_CP + codigoPostal).get();

				dataTablesearch = doc.getElementById("dataTablesearch");

				if (dataTablesearch != null) {
					trs = dataTablesearch.getElementsByTag("tbody").get(0).getElementsByTag("tr");
					for (Element element : trs) {
						tds = element.getAllElements();
						if (!tds.get(1).text().isEmpty()) {
							zipCodes = new ZipCodes();
							zipCodes.setAsentamiento(tds.get(1).text());
							zipCodes.setCodigoPostal(tds.get(4).text());
							zipCodes.setTipoAsentamiento(tds.get(3).text());
							zipCodes.setMunicipio(tds.get(5).text());
							zipCodes.setCiudad(tds.get(7).text());
							zipCodes.setZona(tds.get(8).text());
							zipCodes.setEstado(tds.get(9).text());
							codesRepository.save(zipCodes);
							resultList.add(zipCodes);
						}
					}
					responseCodigoPostalDto = getResponseCPFromData(resultList);
				} else {
					log.info("[findByCodigoPostal] No se encontraron datos paera este codigo");
					return null;
				}
			} catch (IOException e) {
				log.info("[findByCodigoPostal] Hay un problema con el formato de la pagina fuente");
				e.printStackTrace();
				return null;
			}
		}
		return responseCodigoPostalDto;
	}

	@Override
	public List<ZipCodes> findByAsentamiento(String asentamiento) {
		ResponseCodigoPostalDto responseCodigoPostalDto = new ResponseCodigoPostalDto();
		List<ZipCodes> resultList = new ArrayList<>();
		ZipCodes zipCodes;
		Element dataTablesearch;
		Elements trs, tds;

		resultList = codesRepository.findByAsentamiento(asentamiento);
		Document doc;
		
		try {
			doc = Jsoup.connect(URI_BY_ASENTAMIENTO + asentamiento).get();

			dataTablesearch = doc.getElementById("dataTablesearch");

			if (dataTablesearch != null) {
				trs = dataTablesearch.getElementsByTag("tbody").get(0).getElementsByTag("tr");
				for (Element element : trs) {
					tds = element.getAllElements();
					if (!tds.get(1).text().isEmpty()) {
						zipCodes = new ZipCodes();
						zipCodes.setAsentamiento(tds.get(1).text());
						zipCodes.setCodigoPostal(tds.get(4).text());
						zipCodes.setTipoAsentamiento(tds.get(3).text());
						zipCodes.setMunicipio(tds.get(5).text());
						zipCodes.setCiudad(tds.get(7).text());
						zipCodes.setZona(tds.get(8).text());
						zipCodes.setEstado(tds.get(9).text());
						resultList.add(zipCodes);
					}
				}
			} else {
				log.info("[findByAsentamiento] No se encontraron datos paera este lugar");
				return null;
			}
		} catch (IOException e) {
			log.info("[findByAsentamiento] Hay un problema con el formato de la pagina fuente");
			e.printStackTrace();
			return null;
		}
		return resultList;
	}

	private ResponseCodigoPostalDto getResponseCPFromData(List<ZipCodes> zipCodesList) {
		ResponseCodigoPostalDto responseCodigoPostalDto = new ResponseCodigoPostalDto();
		List<AsentamientoDto> asentamientos = new ArrayList<>();
		AsentamientoDto asentamiento;

		responseCodigoPostalDto.setCodigo_postal(zipCodesList.get(0).getCodigoPostal());
		responseCodigoPostalDto.setMunicipio(zipCodesList.get(0).getMunicipio());
		responseCodigoPostalDto.setCiudad(zipCodesList.get(0).getCiudad());
		responseCodigoPostalDto.setEstado(zipCodesList.get(0).getEstado());

		for (ZipCodes temp : zipCodesList) {
			asentamiento = new AsentamientoDto();
			asentamiento.setAsentamiento(temp.getAsentamiento());
			asentamiento.setTipo_asentamiento(temp.getTipoAsentamiento());
			asentamiento.setZona(temp.getZona());
			asentamientos.add(asentamiento);
		}

		responseCodigoPostalDto.setAsentamientos(asentamientos);

		return responseCodigoPostalDto;
	}

}
