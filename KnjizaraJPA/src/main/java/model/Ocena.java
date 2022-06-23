package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Ocena database table.
 * 
 */
@Entity
@NamedQuery(name="Ocena.findAll", query="SELECT o FROM Ocena o")
public class Ocena implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OcenaPK id;

	private String komentar;

	private int vrednost;

	//bi-directional many-to-one association to Knjiga
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name="Knjiga_idKnjiga", insertable = false, updatable = false)
	private Knjiga knjiga;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name="Korisnik_idKorisnik", insertable = false, updatable = false)
	private Korisnik korisnik;

	public Ocena() {
	}

	public OcenaPK getId() {
		return this.id;
	}

	public void setId(OcenaPK id) {
		this.id = id;
	}

	public String getKomentar() {
		return this.komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public int getVrednost() {
		return this.vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
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
	
	public double getVrednostDouble(){
		return vrednost;
	}

}