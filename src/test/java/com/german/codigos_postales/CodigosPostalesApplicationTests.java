package com.german.codigos_postales;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CodigosPostalesApplicationTests {

//	 @Autowired
//	 private CodesRepository zipCodesRepository;

//	 
//
//	 @Test
//	 public void whenFindInDbByCP(){
//	    ZipCodes zipCodes = new ZipCodes();
//	    zipCodes.setCodigoPostal("01080");
//	    zipCodes.setAsentamiento("Progreso Tizapan");
//	    zipCodes.setMunicipio("Álvaro Obregón");
//	    zipCodes.setEstado("Ciudad de México");
//	    zipCodes.setCiudad("Ciudad de México");
//	    zipCodes.setZona("Urbano");
//
//	    zipCodesRepository.save(zipCodes);
//
//	    List<ZipCodes> zipCodesList = zipCodesRepository.findByCodigoPostal("01080");
//	    Assertions.assertThat(zipCodesList.size()).isEqualTo(1);
//
//	}

}
