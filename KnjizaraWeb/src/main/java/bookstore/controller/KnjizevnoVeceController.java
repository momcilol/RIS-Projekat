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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bookstore.repository.KnjigaRepository;
import bookstore.repository.KnjizevnoVeceRepository;
import model.Knjiga;
import model.KnjizevnoVece;

@Controller
@RequestMapping(value = "/knjizVec")
public class KnjizevnoVeceController {

	@Autowired
	KnjizevnoVeceRepository knjizevnoVeceRepository;

	@Autowired
	KnjigaRepository knjigaRepository;

//	====================== Model Atributi ======================

	@ModelAttribute(value = "knjizevneVeceri")
	public void getSveKnjizevneVeceri(Model model) {
		List<KnjizevnoVece> knjizevnoVeces = knjizevnoVeceRepository.findAll();
		model.addAttribute("knjizevneVeceri", knjizevnoVeces);
	}

//	====================== Model Atributi ======================

//	====================== Request Metode ======================

	@RequestMapping(value = "/zakaziKnjizevnoVece", method = RequestMethod.GET)
	public String zakaziKnjizevnoVece(@RequestParam("idKnjiga") int idKnjiga, Model model) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		model.addAttribute("knjiga", knjiga);
		model.addAttribute("knjizevnoVece", new KnjizevnoVece());
		return "knjizevneVeceri/unosKnjizevneVeceri";
	}

	@RequestMapping(value = "/saveKnjizevnoVece", method = RequestMethod.POST)
	public String saveKnjizevnoVece(@RequestParam("idKnjiga") int idKnjiga,
			@ModelAttribute("knjizevnoVece") KnjizevnoVece knjizevnoVece, HttpServletRequest request) {
		Knjiga knjiga = knjigaRepository.getById(idKnjiga);
		knjiga.addKnjizevnoVece(knjizevnoVece);
		KnjizevnoVece kv = knjizevnoVeceRepository.save(knjizevnoVece);

		String poruka = null;
		if (kv != null) {
			poruka = "Uspesno sacuvano.";
		} else {
			poruka = "Greska! Knjizevno vece nije sacuvan.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("kv", kv);
		return "knjizevneVeceri/unosKnjizevneVeceri";
	}

	@RequestMapping(value = "/deleteKnjizevnoVece", method = RequestMethod.GET)
	public String deleteKnjizevnoVece(@RequestParam("idKnjizevnoVece") int idKnjizevnoVece, 
			RedirectAttributes model) {

		knjizevnoVeceRepository.deleteById(idKnjizevnoVece);
		Optional<KnjizevnoVece> opk = knjizevnoVeceRepository.findById(idKnjizevnoVece);

		KnjizevnoVece k = null;
		String poruka = null;
		if (!opk.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			k = opk.get();
		}

		model.addFlashAttribute("poruka", poruka);
		model.addFlashAttribute("k", k);

		getSveKnjizevneVeceri(model);

		return "redirect:/pretraga/pretraziKnjizevneVeceri";
	}
}
