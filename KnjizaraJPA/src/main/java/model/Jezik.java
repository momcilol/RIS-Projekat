package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Jezik database table.
 * 
 */
@Entity
@NamedQuery(name="Jezik.findAll", query="SELECT j FROM Jezik j")
public class Jezik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idJezik;

	private String naziv;

	//bi-directional many-to-one association to Knjiga
	@OneToMany(mappedBy="jezik", cascade = CascadeType.ALL)
	private List<Knjiga> knjigas;

	public Jezik() {
	}

	public int getIdJezik() {
		return this.idJezik;
	}

	public void setIdJezik(int idJezik) {
		this.idJezik = idJezik;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Knjiga> getKnjigas() {
		return this.knjigas;
	}

	public void setKnjigas(List<Knjiga> knjigas) {
		this.knjigas = knjigas;
	}

	public Knjiga addKnjiga(Knjiga knjiga) {
		getKnjigas().add(knjiga);
		knjiga.setJezik(this);

		return knjiga;
	}

	public Knjiga removeKnjiga(Knjiga knjiga) {
		getKnjigas().remove(knjiga);
		knjiga.setJezik(null);

		return knjiga;
	}

}