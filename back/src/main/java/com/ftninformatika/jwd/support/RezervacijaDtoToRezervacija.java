package com.ftninformatika.jwd.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.service.FestivalService;
import com.ftninformatika.jwd.service.RezervacijeService;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter <RezervacijaDTO, Rezervacija>{
	
	@Autowired
	private RezervacijeService rezService;
	
	@Autowired
	private FestivalService festService;

	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		Rezervacija rez;
		
		if (dto.getId() == null) {
			rez = new Rezervacija();
		}else {
			rez = rezService.findOne(dto.getId());
		}
		
		if (rez != null) {
			rez.setBrojKarata(dto.getBrojKarata());
			rez.setFestival(festService.findOne(dto.getFestivalId()));
			rez.setUkupnaCena(dto.getUkupnaCena());
			rez.setId(dto.getId());
		}
		return rez;
	}

}
