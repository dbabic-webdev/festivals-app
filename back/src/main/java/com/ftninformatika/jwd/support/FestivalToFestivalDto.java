package com.ftninformatika.jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.web.dto.FestivalDTO;

@Component
public class FestivalToFestivalDto implements Converter <Festival, FestivalDTO>{
	
	@Autowired
	private MestoToMestoDto toDto;

	@Override
	public FestivalDTO convert(Festival fest) {
		FestivalDTO dto = new FestivalDTO();
		dto.setBrojKarata(fest.getBrojKarata());
		dto.setCena(fest.getCenaKarte());
		dto.setId(fest.getId());
		dto.setMesto(toDto.convert(fest.getMesto()));
		dto.setNaziv(fest.getNaziv());
		dto.setPocetak(fest.getPocetak().toString());
		dto.setKraj(fest.getKraj().toString());
		return dto;
	}
	
    public List<FestivalDTO> convert(List<Festival> fest){
        List<FestivalDTO> dtoS = new ArrayList<>();

        for(Festival it : fest) {
        	dtoS.add(convert(it));
        }

        return dtoS;
    }

}
