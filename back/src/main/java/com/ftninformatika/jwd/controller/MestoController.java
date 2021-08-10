package com.ftninformatika.jwd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Mesto;
import com.ftninformatika.jwd.service.MestoService;
import com.ftninformatika.jwd.support.MestoToMestoDto;
import com.ftninformatika.jwd.web.dto.MestoDTO;

@RestController
@RequestMapping(value = "/api/mesta", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MestoController {
	
	@Autowired
	private MestoService mestoService;
	
	@Autowired
	private MestoToMestoDto toDto;
	
	   
	@GetMapping
    public ResponseEntity<List<MestoDTO>> getAll(){

    	List<Mesto> prevoznici = mestoService.findAll();


        return new ResponseEntity<>(toDto.convert(prevoznici), HttpStatus.OK);
    }

}
