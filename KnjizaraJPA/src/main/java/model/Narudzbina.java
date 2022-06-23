package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.domain.NarudzbinaSearch;

/**
 * The persistent class for the Narudzbina database table.
 * 
 */
@Entity
@NamedQuery(name = "Narudzbina.findAll", query = "SELECT n FROM Narudzbina n")
public class Narudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNarudzbina;

	@Temporal(TemporalType.DATE)
	private Date datum;

	// bi-directional many-to-one association to Knjiga
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "Knjiga_idKnjiga")
	private Knjiga knjiga;

	// bi-directional many-to-one association to Korisnik
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "Korisnik_idKorisnik")
	private Korisnik korisnik;

	public Narudzbina() {
	}

	public int getIdNarudzbina() {
		return this.idNarudzbina;
	}

	public void setIdNarudzbina(int idNarudzbina) {
		this.idNarudzbina = idNarudzbina;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Knjiga getKnjiga() {
		return this.knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public boolean like(NarudzbinaSearch other) {
		if (other.getNaziv() != null && !other.getNaziv().isBlank()) {
			if (!this.knjiga.getNaziv().contains(other.getNaziv().trim())) {
				return false;
			}
		}
		if (other.getUsername() != null && !other.getUsername().isBlank()) {
			if (!this.korisnik.getUsername().contains(other.getUsername().trim())) {
				return false;
			}
		}
		if (other.getDatumOd() != null && this.getDatum().before(other.getDatumOd())) {
			return false;
		}
		if (other.getDatumDo() != null && this.getDatum().after(other.getDatumDo())) {
			return false;
		}
		return true;
	}

}