package com.ftninformatika.jwd.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.service.FestivalService;
import com.ftninformatika.jwd.service.MestoService;
import com.ftninformatika.jwd.web.dto.FestivalDTO;

@Component
public class FestivalDtoToFestival implements Converter <FestivalDTO, Festival>{
	
	@Autowired 
	private FestivalService festivalService;
	
	@Autowired
	private MestoService mestoService;

	@Override
	public Festival convert(FestivalDTO dto) {
		Festival entity;
		
		if (dto.getId() == null) {
			entity = new Festival();
		}else {
			entity = festivalService.findOne(dto.getId());
		}
		
		if (entity != null) {
			entity.setBrojKarata(dto.getBrojKarata());
			entity.setCenaKarte(dto.getCena());
			entity.setId(dto.getId());
			entity.setKraj(getLocalDate(dto.getKraj()));
			entity.setPocetak(getLocalDate(dto.getPocetak()));
			entity.setNaziv(dto.getNaziv());
			entity.setMesto(mestoService.findOne(dto.getMesto().getId()));
		}
		return entity;
	}
	
    private LocalDate getLocalDate(String datumS) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datum = LocalDate.parse(datumS, formatter);
        return datum;
    }

}
