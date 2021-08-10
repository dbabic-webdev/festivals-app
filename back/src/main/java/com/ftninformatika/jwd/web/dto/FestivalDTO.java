package com.ftninformatika.jwd.web.dto;

public class FestivalDTO {
	
	private Long id;
	
	private String naziv;
	
	private String pocetak;
	private String kraj;
	
	private double cena;
	private int brojKarata;
	private MestoDTO mesto;
	
	
	public FestivalDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getPocetak() {
		return pocetak;
	}


	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}


	public String getKraj() {
		return kraj;
	}


	public void setKraj(String kraj) {
		this.kraj = kraj;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public int getBrojKarata() {
		return brojKarata;
	}


	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}


	public MestoDTO getMesto() {
		return mesto;
	}


	public void setMesto(MestoDTO mesto) {
		this.mesto = mesto;
	}
	

}
