package bookstore.domain;

import java.io.Serializable;
import java.util.Date;

import model.Korisnik;
import model.Uloga;

public class ZaposleniKorisnik implements Serializable {


	private static final long serialVersionUID = 1L;
	

	private int idKorisnik;

	private String adresa;

	private String email;

	private String ime;

	private String password;

	private String prezime;

	private String telefon;

	private String username;

	private Uloga uloga;

	private Date datumZaposlenja;

	private int plata;

	private int radniStaz;
	
	public ZaposleniKorisnik() {}
	
	public ZaposleniKorisnik(Korisnik korisnik) {
		this.idKorisnik = korisnik.getIdKorisnik();
		this.adresa = korisnik.getAdresa();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.username = korisnik.getUsername();
		this.password = korisnik.getPassword();
		this.adresa = korisnik.getAdresa();
		this.email = korisnik.getEmail();
		this.telefon = korisnik.getTelefon();
		this.uloga = korisnik.getUloga();
		this.datumZaposlenja = korisnik.getZaposleni().getDatumZaposlenja();
		this.plata = korisnik.getZaposleni().getPlata();
		this.radniStaz = korisnik.getZaposleni().getRadniStaz();
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public int getRadniStaz() {
		return radniStaz;
	}

	public void setRadniStaz(int radniStaz) {
		this.radniStaz = radniStaz;
	}
	
	

}
