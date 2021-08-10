package com.ftninformatika.jwd.service;

import java.util.List;

import com.ftninformatika.jwd.model.Mesto;

public interface MestoService {
	
	Mesto findOne (Long id);
	
	List <Mesto> findAll();

}
