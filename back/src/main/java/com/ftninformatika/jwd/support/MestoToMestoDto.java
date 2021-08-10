package com.ftninformatika.jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Mesto;
import com.ftninformatika.jwd.web.dto.MestoDTO;

@Component
public class MestoToMestoDto implements Converter <Mesto, MestoDTO>{

	@Override
	public MestoDTO convert(Mesto source) {
		MestoDTO dto = new MestoDTO();
		dto.setDrzava(source.getDrzava());
		dto.setGrad(source.getGrad());
		dto.setId(source.getId());
		return dto;
	}
	
    public List<MestoDTO> convert(List<Mesto> mesta){
        List<MestoDTO> dtos = new ArrayList<>();

        for(Mesto k : mesta) {
        	MestoDTO dto = convert(k);
        	dtos.add(dto);
        }

        return dtos;
    }

}
