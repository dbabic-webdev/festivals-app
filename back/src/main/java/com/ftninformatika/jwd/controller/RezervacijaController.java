package com.ftninformatika.jwd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.service.FestivalService;
import com.ftninformatika.jwd.service.RezervacijeService;
import com.ftninformatika.jwd.support.RezervacijaDtoToRezervacija;
import com.ftninformatika.jwd.support.RezervacijaToRezervacijaDto;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class RezervacijaController {
	
	@Autowired
	private RezervacijeService rezervacijaService;
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private RezervacijaToRezervacijaDto toDto;
	
	
	@Autowired
	private RezervacijaDtoToRezervacija toRez;
	
    @GetMapping("/{id}")
    public ResponseEntity<RezervacijaDTO> getOne(@PathVariable Long id){
       Rezervacija rez = rezervacijaService.findOne(id);

        if(rez != null) {
            return new ResponseEntity<>(toDto.convert(rez), HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO dto){
    	Festival fest = festivalService.findOne(dto.getFestivalId());
    	
    	if (dto.getBrojKarata() > fest.getBrojKarata()) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	
        Rezervacija rez = toRez.convert(dto);
        Rezervacija saved = rezervacijaService.save(rez);

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }

}
