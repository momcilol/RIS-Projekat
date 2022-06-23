package bookstore.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Jezik;
import model.Kategorija;
import model.Pisac;

public class KnjigaImage implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idKnjiga;
	private int godinaIzdanja;
	private String izdavac;
	private String naziv;
	private String opis;
	private MultipartFile slika;
	private Jezik jezik;
	private List<Kategorija> kategorijas;
	private Pisac pisac;

	public KnjigaImage() {
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

	public MultipartFile getSlika() {
		return this.slika;
	}

	public void setSlika(MultipartFile slika) {
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

}