package bookstore.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.domain.ZaposleniKorisnik;
import bookstore.repository.KorisnikRepository;
import bookstore.repository.ZaposleniRepository;
import model.Korisnik;
import model.Zaposleni;

@Controller
@RequestMapping(value = "/adminZaposleni")
public class ZaposleniController {

	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	ZaposleniRepository zaposleniRepository;
	
	
	@ModelAttribute("sviZaposleni")
	public void getSviZaposleni(Model model) {
		model.addAttribute("sviZaposleni", korisnikRepository.findZaposleniKorisniks());
	}
	
	
	@RequestMapping(value = "/sviZaposleni", method = RequestMethod.GET)
	public String sviZaposleni() {
		return "administracijaZaposlenih/prikazZaposlenih";
	}
	
	@RequestMapping(value = "/prikazDetalja", method = RequestMethod.GET)
	public String prikazDetalja(@RequestParam("id") int idKorisnik, Model model) {
		Korisnik korisnik = korisnikRepository.getById(idKorisnik);
		ZaposleniKorisnik zaposleniKorisnik = new ZaposleniKorisnik(korisnik);
		model.addAttribute("zd", zaposleniKorisnik);
		return "administracijaZaposlenih/prikazDetaljaZaposlenog";
	}
	
	@RequestMapping(value = "/deleteZaposleni", method = RequestMethod.GET)
	public String deleteZaposleni(@RequestParam("idKorisnik") int idKorisnik, HttpServletRequest request, Model model) {
		korisnikRepository.deleteById(idKorisnik);
		Optional<Korisnik> opk = korisnikRepository.findById(idKorisnik);

		Korisnik k = null;
		String poruka = null;
		if (!opk.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			k = opk.get();
		}

		request.setAttribute("poruka", poruka);
		request.setAttribute("k", k);
		getSviZaposleni(model);
		return "administracijaZaposlenih/prikazZaposlenih";
	}
	
	@RequestMapping(value = "/azurirajZaposlenog", method = RequestMethod.GET)
	public String azurirajZaposlenog(@RequestParam("idKorisnik") int idKorisnik, Model model, HttpServletRequest request) {
		Korisnik korisnik = korisnikRepository.getById(idKorisnik);
		ZaposleniKorisnik zaposleniKorisnik = new ZaposleniKorisnik(korisnik);
		model.addAttribute("zd", zaposleniKorisnik);
		request.getSession().setAttribute("id", idKorisnik);
		return "administracijaZaposlenih/azuriranjePodatakaZaposlenog";
	}
	
	@RequestMapping(value = "/updateZaposleni", method = RequestMethod.POST)
	public String updateZaposleni(@ModelAttribute("zd") ZaposleniKorisnik zaposleniKorisnik, HttpServletRequest request) {
		int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
		Zaposleni zaposleni = zaposleniRepository.getById(id);
		zaposleni.setRadniStaz(zaposleniKorisnik.getRadniStaz());
		zaposleni.setPlata(zaposleniKorisnik.getPlata());
		zaposleniRepository.save(zaposleni);
		request.setAttribute("poruka", "Uspesno azuriranje podataka.");
		return "administracijaZaposlenih/azuriranjePodatakaZaposlenog";
	}
	
	
}
