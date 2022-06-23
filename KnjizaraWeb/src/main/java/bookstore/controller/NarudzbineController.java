package bookstore.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.repository.NarudzbinaRepository;
import model.Narudzbina;
import model.domain.NarudzbinaSearch;

@Controller
@RequestMapping(value = "/narudzbine")
public class NarudzbineController {

	@Autowired
	NarudzbinaRepository narudzbinaRepository;

//	====================== Model Atributi ======================

	@ModelAttribute(value = "narudzbinaSearch")
	public void getNarudzbinaSearch(Model model) {
		NarudzbinaSearch narudzbinaSearch = new NarudzbinaSearch();
		model.addAttribute("narudzbinaSearch", narudzbinaSearch);
	}

	@ModelAttribute(value = "narudzbine")
	public void getSveNarudzbine(Model model) {
		List<Narudzbina> narudzbinas = narudzbinaRepository.findAll();
		model.addAttribute("narudzbine", narudzbinas);
	}

//	====================== Model Atributi ======================

//	====================== Request Metode ======================

	@RequestMapping(value = "/pretraziNarudzbine", method = RequestMethod.GET)
	public String pretraziNarudzbine() {
		return "administracijaNarudzbina/pretragaNarudzbina";
	}

	@RequestMapping(value = "/searchNarudzbina", method = RequestMethod.GET)
	public String searchKnjiga(@ModelAttribute("narudzbinaSearch") NarudzbinaSearch narudzbinaSearch,
			HttpServletRequest request, Model model) {

		List<Narudzbina> narudzbinas = narudzbinaRepository.findAll().stream().filter((n) -> n.like(narudzbinaSearch))
				.toList();
		model.addAttribute("narudzbine", narudzbinas);

		return "administracijaNarudzbina/pretragaNarudzbina";
	}

	@RequestMapping(value = "/deleteNarudzbina", method = RequestMethod.GET)
	public String deleteNarudzbina(@RequestParam(value = "idNarudzbina") int idNarudzbina, HttpServletRequest request,
			Model model) {
		narudzbinaRepository.deleteById(idNarudzbina);
		Optional<Narudzbina> opn = narudzbinaRepository.findById(idNarudzbina);

		Narudzbina n = null;
		String poruka = null;
		if (!opn.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			n = opn.get();
		}

		request.setAttribute("poruka", poruka);
		request.setAttribute("n", n);
		getNarudzbinaSearch(model);
		getSveNarudzbine(model);
		return "administracijaNarudzbina/pretragaNarudzbina";
	}

}
