package com.ftninformatika.jwd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Festival;
import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.repository.RezervacijaRepository;
import com.ftninformatika.jwd.service.FestivalService;
import com.ftninformatika.jwd.service.RezervacijeService;

@Service
public class JpaRezervacijaService implements RezervacijeService{
	
	@Autowired
	private RezervacijaRepository repo;
	
	@Autowired
	private FestivalService festivalService;

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		Festival festival = rezervacija.getFestival();
		double ukupnaCena = festival.getCenaKarte() * rezervacija.getBrojKarata();
		rezervacija.setUkupnaCena(ukupnaCena);
		
		int brojMesta = festival.getBrojKarata() - rezervacija.getBrojKarata();
		festival.setBrojKarata(brojMesta);
		
		festivalService.update(festival);
		
		return repo.save(rezervacija);
	}

	@Override
	public Rezervacija findOne(Long id) {
		return repo.findOneById(id);
	}

}
