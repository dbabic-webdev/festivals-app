package com.ftninformatika.jwd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.service.FestivalService;
import com.ftninformatika.jwd.support.FestivalDtoToFestival;
import com.ftninformatika.jwd.support.FestivalToFestivalDto;
import com.ftninformatika.jwd.web.dto.FestivalDTO;

@RestController
@RequestMapping(value = "/api/festivali", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class FestivalController {
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private FestivalToFestivalDto toDto;
	
	@Autowired
	private FestivalDtoToFestival toFestival;
	
	  @GetMapping
	  public ResponseEntity<List<FestivalDTO>> getAll(
			  @RequestParam(value = "naziv", required = false) String naziv,
			  @RequestParam(value = "mestoId", required = false) Long mestoId,
	          @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	  	 Page<Festival> page = null;
	  	 
	  	 if (naziv != null || mestoId != null) {
	  		 page = festivalService.search(naziv, mestoId, pageNo);
	  	 }else {
	  		 page = festivalService.findAll(pageNo);
	  	 }

	      HttpHeaders headers = new HttpHeaders();
	      headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	      return new ResponseEntity<>(toDto.convert(page.getContent()),headers, HttpStatus.OK);
	  }
	  
	  
	  
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<FestivalDTO> create(@Valid @RequestBody FestivalDTO dto){
		  Festival fest = toFestival.convert(dto);
	      Festival saved = festivalService.save(fest);

	      return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	  }
	  
	  
	  @GetMapping("/{id}")
	  public ResponseEntity<FestivalDTO> getOne(@PathVariable Long id){
	     Festival fest = festivalService.findOne(id);

	      if(fest != null) {
	          return new ResponseEntity<>(toDto.convert(fest), HttpStatus.OK);
	      }else {
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  
	  @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<FestivalDTO> update(@PathVariable Long id, @Valid @RequestBody FestivalDTO dto){

	      if(!id.equals(dto.getId())) {
	          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	      }

	      Festival linija = toFestival.convert(dto);
	      Festival saved = festivalService.update(linija);

	      return new ResponseEntity<>(toDto.convert(saved),HttpStatus.OK);
	  }
	  
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> delete(@PathVariable Long id){
	      Festival deleted = festivalService.delete(id);

	      if(deleted != null) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } else {
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  

}
