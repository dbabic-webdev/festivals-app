package com.ftninformatika.jwd.service;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.model.Festival;

public interface FestivalService {
	
	Page<Festival> findAll (int pageNo);
	
	Page <Festival> search (String naziv, Long mestoId, int pageNo);
	
	Festival findOne (Long id);
	
	Festival save (Festival festival);
	
	Festival delete (Long id);
	
	Festival update (Festival festival);
	

}
