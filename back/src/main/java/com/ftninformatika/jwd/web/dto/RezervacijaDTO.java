package com.ftninformatika.jwd.web.dto;

public class RezervacijaDTO {
	
	private Long id;
	
	private int brojKarata;
	private double ukupnaCena;
	private Long festivalId;
	private String festivalNaziv;
	
	
	public RezervacijaDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getBrojKarata() {
		return brojKarata;
	}


	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}


	public double getUkupnaCena() {
		return ukupnaCena;
	}


	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}


	public Long getFestivalId() {
		return festivalId;
	}


	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}


	public String getFestivalNaziv() {
		return festivalNaziv;
	}


	public void setFestivalNaziv(String festivalNaziv) {
		this.festivalNaziv = festivalNaziv;
	}
	
	
	
	

}
