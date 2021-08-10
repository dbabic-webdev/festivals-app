package com.ftninformatika.jwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Mesto;
import com.ftninformatika.jwd.repository.MestoRepository;
import com.ftninformatika.jwd.service.MestoService;

@Service
public class JpaMestoService implements MestoService{
	
	@Autowired
	private MestoRepository mestoRepository;

	@Override
	public Mesto findOne(Long id) {
		return mestoRepository.findOneById(id);
	}

	@Override
	public List<Mesto> findAll() {
		return mestoRepository.findAll();
	}

}
