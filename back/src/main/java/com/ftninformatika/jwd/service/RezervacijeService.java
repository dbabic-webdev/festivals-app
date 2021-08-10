package com.ftninformatika.jwd.service;

import com.ftninformatika.jwd.model.Rezervacija;

public interface RezervacijeService {
	
	Rezervacija save (Rezervacija rezervacija);
	
	Rezervacija findOne (Long id);

}
