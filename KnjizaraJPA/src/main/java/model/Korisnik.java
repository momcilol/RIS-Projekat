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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * The persistent class for the Korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String adresa;

	private String email;

	private String ime;

	private String password;

	private String prezime;

	private String telefon;

	private String username;

	//bi-directional many-to-many association to Kategorija
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(
		name="OmiljeneKategorije"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Kategorija_idKategorija")
			}
		)
	private List<Kategorija> kategorijas;

	//bi-directional many-to-many association to Knjiga
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(
		name="OmiljeneKnjige"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Knjiga_idKnjiga")
			}
		)
	private List<Knjiga> knjigas;

	//bi-directional many-to-many association to KnjizevnoVece
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(
		name="KorisnikKnjizevnoVece"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="KnjizevnoVece_idKnjizevnoVece")
			}
		)
	private List<KnjizevnoVece> knjizevnoVeces;

	//bi-directional many-to-one association to Uloga
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name="Uloga_idUloga")
	private Uloga uloga;

	//bi-directional many-to-one association to Narudzbina
	@OneToMany(mappedBy="korisnik", cascade = CascadeType.ALL)
	private List<Narudzbina> narudzbinas;

	//bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy="korisnik", cascade = CascadeType.ALL)
	private List<Ocena> ocenas;

	//bi-directional one-to-one association to Zaposleni
	@OneToOne(mappedBy="korisnik", cascade = CascadeType.ALL, orphanRemoval = true)
	private Zaposleni zaposleni;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Kategorija> getKategorijas() {
		return this.kategorijas;
	}

	public void setKategorijas(List<Kategorija> kategorijas) {
		this.kategorijas = kategorijas;
	}

	public List<Knjiga> getKnjigas() {
		return this.knjigas;
	}

	public void setKnjigas(List<Knjiga> knjigas) {
		this.knjigas = knjigas;
	}

	public List<KnjizevnoVece> getKnjizevnoVeces() {
		return this.knjizevnoVeces;
	}

	public void setKnjizevnoVeces(List<KnjizevnoVece> knjizevnoVeces) {
		this.knjizevnoVeces = knjizevnoVeces;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Narudzbina> getNarudzbinas() {
		return this.narudzbinas;
	}

	public void setNarudzbinas(List<Narudzbina> narudzbinas) {
		this.narudzbinas = narudzbinas;
	}

	public Narudzbina addNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().add(narudzbina);
		narudzbina.setKorisnik(this);

		return narudzbina;
	}

	public Narudzbina removeNarudzbina(Narudzbina narudzbina) {
		getNarudzbinas().remove(narudzbina);
		narudzbina.setKorisnik(null);

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
		ocena.setKorisnik(this);

		return ocena;
	}

	public Ocena removeOcena(Ocena ocena) {
		getOcenas().remove(ocena);
		ocena.setKorisnik(null);

		return ocena;
	}

	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Korisnik)) {
			return false;
		}
		Korisnik korisnik = (Korisnik) obj;
		if (this.idKorisnik != korisnik.idKorisnik) {
			return false;
		}
		return true;
	}

	public String getFullName() {
		return ime + " " + prezime;

	}

}