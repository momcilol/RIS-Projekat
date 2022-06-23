package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Ocena database table.
 * 
 */
@Embeddable
public class OcenaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Korisnik_idKorisnik", insertable=false, updatable=false)
	private int korisnik_idKorisnik;

	@Column(name="Knjiga_idKnjiga", insertable=false, updatable=false)
	private int knjiga_idKnjiga;

	public OcenaPK() {
	}
	public int getKorisnik_idKorisnik() {
		return this.korisnik_idKorisnik;
	}
	public void setKorisnik_idKorisnik(int korisnik_idKorisnik) {
		this.korisnik_idKorisnik = korisnik_idKorisnik;
	}
	public int getKnjiga_idKnjiga() {
		return this.knjiga_idKnjiga;
	}
	public void setKnjiga_idKnjiga(int knjiga_idKnjiga) {
		this.knjiga_idKnjiga = knjiga_idKnjiga;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OcenaPK)) {
			return false;
		}
		OcenaPK castOther = (OcenaPK)other;
		return 
			(this.korisnik_idKorisnik == castOther.korisnik_idKorisnik)
			&& (this.knjiga_idKnjiga == castOther.knjiga_idKnjiga);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.korisnik_idKorisnik;
		hash = hash * prime + this.knjiga_idKnjiga;
		
		return hash;
	}
}