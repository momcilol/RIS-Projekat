package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import model.domain.KnjigaSearch;

/**
 * The persistent class for the Knjiga database table.
 * 
 */
@Entity
@NamedQuery(name = "Knjiga.findAll", query = "SELECT k FROM Knjiga k")
public class Knjiga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKnjiga;

	private int godinaIzdanja;

	private String izdavac;

	private String naziv;

	private String opis;

	@Lob
	private byte[] slika;

	// bi-directional many-to-one association to Jezik
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "Jezik_idJezik")
	private Jezik jezik;

	// bi-directional many-to-many association to Kategorija
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(name = "KategorijaKnjiga", joinColumns = {
			@JoinColumn(name = "Knjiga_idKnjiga") }, inverseJoinColumns = {
					@JoinColumn(name = "Kategorija_idKategorija") })
	private List<Kategorija> kategorijas;

	// bi-directional many-to-one association to Pisac
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "Pisac_idPisac")
	private Pisac pisac;

	// bi-directional many-to-one association to KnjizevnoVece
	@OneToMany(mappedBy = "knjiga", cascade = CascadeType.ALL)
	private List<KnjizevnoVece> knjizevnoVeces;

	// bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy = "knjigas", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	private List<Korisnik> korisniks;

	// bi-directional many-to-one association to Narudzbina
	@OneToMany(mappedBy = "knjiga", cascade = CascadeType.ALL)
	private List<Narudzbina> narudzbinas;

	// bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy = "knjiga", cascade = CascadeType.ALL)
	private List<Ocena> ocenas;

	public Knjiga() {
	}

	public int getIdKnjiga() {
		return this.idKnjiga;
	}

	public void setIdKnjiga(int idKnjiga) {
		this.idKnjiga = idKnjiga;
	}

	public int getGodinaIzdanja() {
		return this.godinaIzdanja;
	}

	public void setGodinaIzdanja(int godinaIzdanja) {
		this.godinaIzdanja = godinaIzdanja;
	}

	public String getIzdavac() {
		return this.izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public Jezik getJezik() {
		return this.jezik;
	}

	public void setJezik(Jezik jezik) {
		this.jezik = jezik;
	}

	public List<Kategorija> getKategorijas() {
		return this.kategorijas;
	}

	public void setKategorijas(List<Kategorija> kategorijas) {
		this.kategorijas = kategorijas;
	}

	public Pisac getPisac() {
		return this.pisac;
	}

	public void setPisac(Pisac pisac) {
		this.pisac = pisac;
	}

	public List<KnjizevnoVece> getKnjizevnoVeces() {
		return this.knjizevnoVeces;
	}

	public void setKnjizevnoVeces(List<KnjizevnoVece> knjizevnoVeces) {
		this.knjizevnoVeces = knjizevnoVeces;
	}

	public KnjizevnoVece addKnjizevnoVece(KnjizevnoVece knjizevnoVece) {
		getKnjizevnoVeces().add(knjizevnoVece);
		knjizevnoVece.setKnjiga(this);

		return knjizevnoVece;
	}

	public KnjizevnoVece removeKnjizevnoVece(KnjizevnoVece knjizevnoVece) {
		getKnjizevnoVeces().remove(knjizevnoVece);
		knjizevnoVece.setKnjiga(null);

		return knjizevnoVece;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public List<Narudzbina> getNarudzbinas() {
		return this.narudzbinas;
	}

	public void setNarudzbinas(List<Narudzbina> narudzbinas) {
		this.narudzbinas = narudzbinas;
	}

	public Narudzbina addNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().add(narudzbina);
		narudzbina.setKnjiga(this);

		return narudzbina;
	}

	public Narudzbina removeNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().remove(narudzbina);
		narudzbina.setKnjiga(null);

		return narudzbina;
	}

	public List<Ocena> getOcenas() {
		return this.ocenas;
	}

	public void setOcenas(List<Ocena> ocenas) {
		this.ocenas = ocenas;
	}

	public Ocena addOcena(Ocena ocena) {
		getOcenas().add(ocena);
		ocena.setKnjiga(this);

		return ocena;
	}

	public Ocena removeOcena(Ocena ocena) {
		getOcenas().remove(ocena);
		ocena.setKnjiga(null);

		return ocena;
	}

	public String getAverageOcena() {
		double avg = ocenas.stream().mapToDouble(Ocena::getVrednostDouble).summaryStatistics().getAverage();

		return String.format("%,.2f", avg);

	}
	
	public boolean like(KnjigaSearch other) {
		if (other.getNaziv() != null && !other.getNaziv().isBlank()) {
			if (!this.naziv.contains(other.getNaziv().trim())) {
				return false;
			}
		}
		if (other.getPisac() != 0) {
			if (this.pisac.getIdPisac() != other.getPisac()) {
				return false;
			}
		}
		if (other.getJezik() != 0) {
			if (this.jezik.getIdJezik() != other.getJezik()) {
				return false;
			}
		}
		if (!other.getKategorijas().isEmpty()) {
			for (Kategorija kategorija : other.getKategorijas()) {
				if (!this.kategorijas.contains(kategorija)) {
					return false;
				}
			}
		}
		return true;
	}
	

}