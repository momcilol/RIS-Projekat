package bookstore.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.repository.KnjigaRepository;
import bookstore.repository.KnjizevnoVeceRepository;
import bookstore.repository.KorisnikRepository;
import bookstore.repository.NarudzbinaRepository;
import bookstore.repository.OcenaRepository;
import model.Knjiga;
import model.KnjizevnoVece;
import model.Korisnik;
import model.Narudzbina;
import model.Ocena;
import model.OcenaPK;

@Controller
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired
	KnjigaRepository knjigaRepository;

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	NarudzbinaRepository narudzbinaRepository;

	@Autowired
	OcenaRepository ocenaRepository;

	@Autowired
	KnjizevnoVeceRepository knjizevnoVeceRepository;

//	====================== Model Atributi ======================

	public void getKnjiga(int idKnjiga, Model model) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		model.addAttribute("k", knjiga);
	}

	public void getOcena(int idKnjiga, Model model, Principal principal) {
		Ocena ocena = ocenaRepository.findByUsernameAndKnjiga(idKnjiga, principal.getName());
		if (ocena == null) {
			ocena = new Ocena();
		}
		model.addAttribute("ocena", ocena);
	}

	public void isOmiljenaKnjiga(int idKnjiga, Model model, Principal principal) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		Boolean fav = knjiga.getKorisniks().contains(korisnik);
		System.out.println("Ja sam: " + fav);
		model.addAttribute("fav", fav);
	}

	public void isPrijavljenKnjizevnoVece(int idKnjizevnoVece, Model model, Principal principal) {
		KnjizevnoVece knjizevnoVece = knjizevnoVeceRepository.getById(idKnjizevnoVece);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		Boolean pr = knjizevnoVece.getKorisniks().contains(korisnik);
		model.addAttribute("pr", pr);
	}

//	====================== Model Atributi ======================

//	====================== Request Metode ======================

	@RequestMapping(value = "/prikaziDetalje", method = RequestMethod.GET)
	public String prikaziDetalje(@RequestParam(value = "idKnjiga") int idKnjiga, Model model, Principal principal) {
		getKnjiga(idKnjiga, model);
		getOcena(idKnjiga, model, principal);
		isOmiljenaKnjiga(idKnjiga, model, principal);
		return "pretragaKataloga/detaljiKnjige";
	}

//	====================== Narudzbina crud =====================

	@RequestMapping(value = "/naruciKnjigu", method = RequestMethod.GET)
	public String naruciKnjigu(@RequestParam(value = "idKnjiga") int idKnjiga, HttpServletRequest request, Model model,
			Principal principal) {
		Narudzbina narudzbina = new Narudzbina();
		narudzbina.setDatum(new Date());

		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());

		knjiga.addNarudzbina(narudzbina);
		korisnik.addNarudzbina(narudzbina);

		Narudzbina n = narudzbinaRepository.save(narudzbina);

		String poruka = null;
		if (n != null) {
			poruka = "Uspesno naruceno.";
		} else {
			poruka = "Greska! Naruzbina nije sacuvan.";
		}
		model.addAttribute("n", n);
		model.addAttribute("poruka", poruka);
		model.addAttribute("k", knjiga);

		getOcena(idKnjiga, model, principal);
		isOmiljenaKnjiga(idKnjiga, model, principal);

		return "pretragaKataloga/detaljiKnjige";
	}

	@RequestMapping(value = "/oceniKnjigu", method = RequestMethod.POST)
	public String oceniKnjigu(@RequestParam(value = "idKnjiga") int idKnjiga,
			@ModelAttribute(value = "ocena") Ocena ocena, Model model, Principal principal) {

		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		knjiga.addOcena(ocena);

		OcenaPK ocenaPK = new OcenaPK();
		ocenaPK.setKnjiga_idKnjiga(idKnjiga);

		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		ocenaPK.setKorisnik_idKorisnik(korisnik.getIdKorisnik());

		ocena.setId(ocenaPK);
		korisnik.addOcena(ocena);

		Ocena o = ocenaRepository.save(ocena);

		String poruka = null;
		if (o != null) {
			poruka = "Ocena sacuvana.";
		} else {
			poruka = "Greska! Ocena nije sacuvan.";
		}
		model.addAttribute("ocena", o);
		model.addAttribute("poruka", poruka);

		model.addAttribute("k", knjiga);

		isOmiljenaKnjiga(idKnjiga, model, principal);

		return "pretragaKataloga/detaljiKnjige";
	}

//	====================== Narudzbina crud =====================

//	====================== Omiljena knjiga crud ================

	@RequestMapping(value = "/omiljenaKnjiga", method = RequestMethod.GET)
	public String omiljenaKnjiga(@RequestParam(value = "idKnjiga") int idKnjiga, Model model, Principal principal) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		knjiga.getKorisniks().add(korisnik);
		korisnik.getKnjigas().add(knjiga);
		knjigaRepository.save(knjiga);
		korisnikRepository.save(korisnik);

		Boolean fav = true;
		model.addAttribute("fav", fav);
		model.addAttribute("k", knjiga);

		getOcena(idKnjiga, model, principal);

		return "pretragaKataloga/detaljiKnjige";
	}

	@RequestMapping(value = "/ukloniOmiljenaKnjiga", method = RequestMethod.GET)
	public String ukolniOmiljenaKnjiga(@RequestParam(value = "idKnjiga") int idKnjiga, Model model,
			Principal principal) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		knjiga.getKorisniks().remove(korisnik);
		korisnik.getKnjigas().remove(knjiga);
		knjigaRepository.save(knjiga);
		korisnikRepository.save(korisnik);

		Boolean fav = false;
		model.addAttribute("fav", fav);

		model.addAttribute("k", knjiga);

		getOcena(idKnjiga, model, principal);

		return "pretragaKataloga/detaljiKnjige";
	}

	@RequestMapping(value = "/prikazOmiljenihKnjiga", method = RequestMethod.GET)
	public String prikazOmiljenihKnjiga(Principal principal, Model model) {
		System.out.println("1");
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		System.out.println("2");
		List<Knjiga> knjige = korisnik.getKnjigas();
		System.out.println("3");
		model.addAttribute("omkn", knjige);
		System.out.println("4");
		return "korisnikAktivnosti/omiljeneKnjige";
	}

//	====================== Omiljena knjiga crud ================

//	====================== Knjizevna vecer =====================

	@RequestMapping(value = "/prikaziDetaljeKV", method = RequestMethod.GET)
	public String prikaziDetaljeKV(@RequestParam("idKnjizevnoVece") int idKnjizevnoVece, Model model,
			Principal principal) {
		KnjizevnoVece knjizevnoVece = knjizevnoVeceRepository.getById(idKnjizevnoVece);
		model.addAttribute("kv", knjizevnoVece);
		isPrijavljenKnjizevnoVece(idKnjizevnoVece, model, principal);
		return "knjizevneVeceri/detaljiKnjizevneVeceri";
	}

	@RequestMapping(value = "/prijaviKnjizevnuVecer", method = RequestMethod.GET)
	public String prijaviKnjizevnuVecer(@RequestParam(value = "idKnjizevnoVece") int idKnjizevnoVece, Model model,
			Principal principal) {
		KnjizevnoVece knjizevnoVece = knjizevnoVeceRepository.getById(idKnjizevnoVece);
		Boolean pr;
		String poruka = null;
		if (knjizevnoVece.getBrojSlobodnihMesta() > 0) {
			Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
			knjizevnoVece.getKorisniks().add(korisnik);
			korisnik.getKnjizevnoVeces().add(knjizevnoVece);
			knjizevnoVeceRepository.save(knjizevnoVece);
			korisnikRepository.save(korisnik);

			pr = true;
			poruka = "Uspesno ste prijavljeni.";
		} else {
			pr = false;
			poruka = "Nazalost nema slobodnih mesta.";
		}
		model.addAttribute("pr", pr);
		model.addAttribute("kv", knjizevnoVece);
		model.addAttribute("poruka", poruka);

		return "knjizevneVeceri/detaljiKnjizevneVeceri";
	}

	@RequestMapping(value = "/odjaviKnjizevnuVecer", method = RequestMethod.GET)
	public String odjaviKnjizevnuVecer(@RequestParam(value = "idKnjizevnoVece") int idKnjizevnoVece, Model model,
			Principal principal) {
		KnjizevnoVece knjizevnoVece = knjizevnoVeceRepository.getById(idKnjizevnoVece);
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		knjizevnoVece.getKorisniks().remove(korisnik);
		korisnik.getKnjizevnoVeces().remove(knjizevnoVece);
		knjizevnoVeceRepository.save(knjizevnoVece);
		korisnikRepository.save(korisnik);

		Boolean pr = false;
		model.addAttribute("pr", pr);
		model.addAttribute("kv", knjizevnoVece);
		model.addAttribute("poruka", "Uspesno ste se odjavili");

		return "knjizevneVeceri/detaljiKnjizevneVeceri";
	}
	
	@RequestMapping(value = "/prikazPrijavljenihKV", method = RequestMethod.GET)
	public String prikazPrijavljenihKV(Principal principal, Model model) {
		System.out.println("1");
		Korisnik korisnik = korisnikRepository.findByUsername(principal.getName());
		System.out.println("2");
		List<KnjizevnoVece> knjizevnoVeces = korisnik.getKnjizevnoVeces();
		System.out.println("3");
		model.addAttribute("prkv", knjizevnoVeces);
		System.out.println("4");
		return "korisnikAktivnosti/prijavljeneKnjizevneVeceri";
	}

}
