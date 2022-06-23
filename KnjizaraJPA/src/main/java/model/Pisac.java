package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Pisac database table.
 * 
 */
@Entity
@NamedQuery(name="Pisac.findAll", query="SELECT p FROM Pisac p")
public class Pisac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPisac;

	private int godinaRodjenja;

	private int godinaSmrti;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Knjiga
	@OneToMany(mappedBy="pisac", cascade = CascadeType.ALL)
	private List<Knjiga> knjigas;

	public Pisac() {
	}

	public int getIdPisac() {
		return this.idPisac;
	}

	public void setIdPisac(int idPisac) {
		this.idPisac = idPisac;
	}

	public int getGodinaRodjenja() {
		return this.godinaRodjenja;
	}

	public void setGodinaRodjenja(int godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public int getGodinaSmrti() {
		return this.godinaSmrti;
	}

	public void setGodinaSmrti(int godinaSmrti) {
		this.godinaSmrti = godinaSmrti;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Knjiga> getKnjigas() {
		return this.knjigas;
	}

	public void setKnjigas(List<Knjiga> knjigas) {
		this.knjigas = knjigas;
	}

	public Knjiga addKnjiga(Knjiga knjiga) {
		getKnjigas().add(knjiga);
		knjiga.setPisac(this);

		return knjiga;
	}

	public Knjiga removeKnjiga(Knjiga knjiga) {
		getKnjigas().remove(knjiga);
		knjiga.setPisac(null);

		return knjiga;
	}

	public String getFullName() {
		return ime + " " + prezime;

	}
}