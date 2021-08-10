package com.ftninformatika.jwd.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter <Rezervacija, RezervacijaDTO>{

	@Override
	public RezervacijaDTO convert(Rezervacija rez) {
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setBrojKarata(rez.getBrojKarata());
		dto.setFestivalId(rez.getFestival().getId());
		dto.setFestivalNaziv(rez.getFestival().getNaziv());
		dto.setId(rez.getId());
		dto.setUkupnaCena(rez.getUkupnaCena());
		return dto;
	}

}
