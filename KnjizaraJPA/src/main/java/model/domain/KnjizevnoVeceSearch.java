package model.domain;

import java.io.Serializable;
import java.util.Date;

public class KnjizevnoVeceSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String naziv;
	private Date datumOd;
	private Date datumDo;
	private String mesto;
	
	public KnjizevnoVeceSearch() {
		// TODO Auto-generated constructor stub
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	
	

}
