package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Zaposleni database table.
 * 
 */
@Entity
@NamedQuery(name="Zaposleni.findAll", query="SELECT z FROM Zaposleni z")
public class Zaposleni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Korisnik_idKorisnik")
	private int korisnik_idKorisnik;

	@Temporal(TemporalType.DATE)
	private Date datumZaposlenja;

	private int plata;

	private int radniStaz;

	//bi-directional one-to-one association to Korisnik
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik korisnik;

	public Zaposleni() {
	}

	public int getKorisnik_idKorisnik() {
		return this.korisnik_idKorisnik;
	}

	public void setKorisnik_idKorisnik(int korisnik_idKorisnik) {
		this.korisnik_idKorisnik = korisnik_idKorisnik;
	}

	public Date getDatumZaposlenja() {
		return this.datumZaposlenja;
	}

	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public int getPlata() {
		return this.plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public int getRadniStaz() {
		return this.radniStaz;
	}

	public void setRadniStaz(int radniStaz) {
		this.radniStaz = radniStaz;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}