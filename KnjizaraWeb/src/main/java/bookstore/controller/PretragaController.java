package bookstore.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.repository.JezikRepository;
import bookstore.repository.KategorijaRepository;
import bookstore.repository.KnjigaRepository;
import bookstore.repository.KnjizevnoVeceRepository;
import bookstore.repository.PisacRepository;
import model.Jezik;
import model.Kategorija;
import model.Knjiga;
import model.KnjizevnoVece;
import model.Pisac;
import model.domain.KnjigaSearch;
import model.domain.KnjizevnoVeceSearch;

@Controller
@RequestMapping(value = "/pretraga")
public class PretragaController {

	@Autowired
	KnjigaRepository knjigaRepository;

	@Autowired
	PisacRepository pisacRepository;

	@Autowired
	JezikRepository jezikRepository;

	@Autowired
	KategorijaRepository kategorijaRepository;

	@Autowired
	KnjizevnoVeceRepository knjizevnoVeceRepository;

//	====================== Model Atributi ======================

	@ModelAttribute(value = "sviPisci")
	public void getSviPisci(Model model) {
		List<Pisac> sviPisci = pisacRepository.findAll();
		model.addAttribute("sviPisci", sviPisci);
	}

	@ModelAttribute(value = "sviJezici")
	public void getSviJezici(Model model) {
		List<Jezik> sviJezici = jezikRepository.findAll();
		model.addAttribute("sviJezici", sviJezici);
	}

	@ModelAttribute(value = "sveKategorije")
	public void getSveKategorije(Model model) {
		List<Kategorija> sveKategorije = kategorijaRepository.findAll();
		model.addAttribute("sveKategorije", sveKategorije);
	}

	@ModelAttribute(value = "knjigaSearch")
	public void getKnjiga(Model model) {
		KnjigaSearch knjigaSearch = new KnjigaSearch();
		model.addAttribute("knjigaSearch", knjigaSearch);
	}

	@ModelAttribute(value = "knjige")
	public void getSveKnjige(Model model) {
		List<Knjiga> sveKnjige = knjigaRepository.findAll();
		model.addAttribute("knjige", sveKnjige);
	}

	@ModelAttribute(value = "knjizevneVeceri")
	public void getSveKnjizevneVeceri(Model model) {
		List<KnjizevnoVece> knjizevnoVeces = knjizevnoVeceRepository.findAll();
		model.addAttribute("knjizevneVeceri", knjizevnoVeces);
	}

	@ModelAttribute(value = "knjizevnoVeceSearch")
	public void getKnjizevnoVeceSearch(Model model) {
		KnjizevnoVeceSearch knjizevnoVeceSearch = new KnjizevnoVeceSearch();
		model.addAttribute("knjizevnoVeceSearch", knjizevnoVeceSearch);
	}

//	====================== Model Atributi ======================

//	====================== Request Metode ======================
	
//	====================== Knjiga pretraga =====================

	@RequestMapping(value = "/pretraziKnjige", method = RequestMethod.GET)
	public String pretraziKnjige() {
		return "pretragaKataloga/pretragaKnjiga";
	}

	@RequestMapping(value = "/getKnjigaPhoto/{id}", method = RequestMethod.GET)
	public void getKnjigaPhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		response.setContentType("image/jpeg");

		Knjiga knjiga = knjigaRepository.findById(id).get();

		InputStream inputStream = new ByteArrayInputStream(knjiga.getSlika());
		IOUtils.copy(inputStream, response.getOutputStream());
	}

	@RequestMapping(value = "/searchKnjiga", method = RequestMethod.GET)
	public String searchKnjiga(@ModelAttribute("knjigaSearch") KnjigaSearch knjigaSearch, HttpServletRequest request,
			Model model) {

		List<Knjiga> knjige = knjigaRepository.findAll().stream().filter((k) -> k.like(knjigaSearch)).toList();
		model.addAttribute("knjige", knjige);

		return "pretragaKataloga/pretragaKnjiga";
	}

	@RequestMapping(value = "/prikaziDetalje", method = RequestMethod.GET)
	public String prikaziDetalje(@RequestParam(value = "idKnjiga") int idKnjiga, Model model) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		model.addAttribute("k", knjiga);
		return "pretragaKataloga/detaljiKnjige";
	}

//	====================== Knjiga pretraga =====================
	
//	====================== Knjizevna vecer pretraga ============

	
	@RequestMapping(value = "/pretraziKnjizevneVeceri", method = RequestMethod.GET)
	public String pretraziKnjizevneVeceri() {
		return "knjizevneVeceri/pretragaKnjizevnihVeceri";
	}

	@RequestMapping(value = "/searchKnjizevnoVece", method = RequestMethod.GET)
	public String searchKnjiga(@ModelAttribute("knjizevnoVeceSearch") KnjizevnoVeceSearch knjizevnoVeceSearch,
			HttpServletRequest request, Model model) {

		List<KnjizevnoVece> knjizevnoVeces = knjizevnoVeceRepository.findAll().stream()
				.filter((k) -> k.like(knjizevnoVeceSearch)).toList();
		model.addAttribute("knjizevneVeceri", knjizevnoVeces);

		return "knjizevneVeceri/pretragaKnjizevnihVeceri";
	}
	
	@RequestMapping(value = "/prikaziDetaljeKV", method = RequestMethod.GET)
	public String prikaziDetaljeKV(@RequestParam("idKnjizevnoVece") int idKnjizevnoVece, Model model) {
		KnjizevnoVece knjizevnoVece = knjizevnoVeceRepository.getById(idKnjizevnoVece);
		model.addAttribute("kv", knjizevnoVece);
		return "knjizevneVeceri/detaljiKnjizevneVeceri";
	
	}
	
//	====================== Knjizevna vecer pretraga ============

}
