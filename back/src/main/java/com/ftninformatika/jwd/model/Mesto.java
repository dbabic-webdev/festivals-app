package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mesto {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String grad;
    
    @Column
    private String drzava;
    
    
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)  
    private List<Festival> festivali = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getGrad() {
		return grad;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}


	public String getDrzava() {
		return drzava;
	}


	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}


	public List<Festival> getFestivali() {
		return festivali;
	}


	public void setFestivali(List<Festival> festivali) {
		this.festivali = festivali;
	}
	
	public void dodajFestival(Festival festival) {
    	this.festivali.add(festival);
        if (!equals(festival.getMesto())) {
        	festival.setMesto(this);
        }
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesto other = (Mesto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Mesto [id=" + id + ", grad=" + grad + ", drzava=" + drzava + ", festivali=" + festivali + "]";
	}
    
    
    
    
    

}
