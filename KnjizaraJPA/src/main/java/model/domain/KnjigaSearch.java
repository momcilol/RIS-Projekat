package model.domain;

import java.io.Serializable;
import java.util.List;

import model.Kategorija;

public class KnjigaSearch implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String naziv;
	private int pisac;
	private int jezik;
	private List<Kategorija> kategorijas;
	
	
	public KnjigaSearch() {}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public int getPisac() {
		return pisac;
	}


	public void setPisac(int pisac) {
		this.pisac = pisac;
	}


	public int getJezik() {
		return jezik;
	}


	public void setJezik(int jezik) {
		this.jezik = jezik;
	}


	public List<Kategorija> getKategorijas() {
		return kategorijas;
	}


	public void setKategorijas(List<Kategorija> kategorijas) {
		this.kategorijas = kategorijas;
	}
	
	
	

}
