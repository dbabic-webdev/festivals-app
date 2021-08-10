package com.ftninformatika.jwd.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.repository.FestivalRepository;
import com.ftninformatika.jwd.service.FestivalService;

@Service
public class JpaFestivalService implements FestivalService{
	
	@Autowired
	private FestivalRepository repo;

	@Override
	public Page<Festival> findAll(int pageNo) {
		return repo.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public Page<Festival> search(String naziv, Long mestoId, int pageNo) {
		if (naziv != "") {
			naziv = "%" + naziv + "%";
		}
		return repo.search(naziv, mestoId, (PageRequest.of(pageNo, 3)));
	}

	@Override
	public Festival findOne(Long id) {
		return repo.findOneById(id);
	}

	@Override
	public Festival save(Festival festival) {
		return repo.save(festival);
	}

	@Override
	public Festival delete(Long id) {
		Optional<Festival> fest = repo.findById(id);
		if (fest.isPresent()) {
			repo.deleteById(id);
			return fest.get();
		}
		return null;
	}

	@Override
	public Festival update(Festival festival) {
		return repo.save(festival);
	}

}
