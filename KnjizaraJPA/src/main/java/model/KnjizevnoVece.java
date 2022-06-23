package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.domain.KnjizevnoVeceSearch;

/**
 * The persistent class for the KnjizevnoVece database table.
 * 
 */
@Entity
@NamedQuery(name = "KnjizevnoVece.findAll", query = "SELECT k FROM KnjizevnoVece k")
public class KnjizevnoVece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKnjizevnoVece;

	private int maxBrPos;

	private String mesto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vreme;

	// bi-directional many-to-one association to Knjiga
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "Knjiga_idKnjiga")
	private Knjiga knjiga;

	// bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy = "knjizevnoVeces", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Korisnik> korisniks;

	public KnjizevnoVece() {
	}

	public int getIdKnjizevnoVece() {
		return this.idKnjizevnoVece;
	}

	public void setIdKnjizevnoVece(int idKnjizevnoVece) {
		this.idKnjizevnoVece = idKnjizevnoVece;
	}

	public int getMaxBrPos() {
		return this.maxBrPos;
	}

	public void setMaxBrPos(int maxBrPos) {
		this.maxBrPos = maxBrPos;
	}

	public String getMesto() {
		return this.mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public Date getVreme() {
		return this.vreme;
	}

	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}

	public Knjiga getKnjiga() {
		return this.knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public String getLepoVreme() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(vreme);
	}
	
	public int getBrojSlobodnihMesta() {
		return maxBrPos - korisniks.size();
	}

	public boolean like(KnjizevnoVeceSearch other) {
		if (other.getNaziv() != null && !other.getNaziv().isBlank()) {
			if (!this.getKnjiga().getNaziv().contains(other.getNaziv().trim())) {
				return false;
			}
		}
		if (other.getMesto() != null && !other.getMesto().isBlank()) {
			if (!this.mesto.contains(other.getMesto().trim())) {
				return false;
			}
		}
		if (other.getDatumOd() != null && this.vreme.before(other.getDatumOd())) {
			return false;
		}
		if (other.getDatumDo() != null && this.vreme.after(other.getDatumDo())) {
			return false;
		}
		return true;
	}
}