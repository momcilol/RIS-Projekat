package bookstore.controller;

import java.io.File;
import java.nio.file.Files;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bookstore.domain.KnjigaImage;
import bookstore.repository.JezikRepository;
import bookstore.repository.KategorijaRepository;
import bookstore.repository.KnjigaRepository;
import bookstore.repository.PisacRepository;
import model.Jezik;
import model.Kategorija;
import model.Knjiga;
import model.Pisac;

@Controller
@RequestMapping(value = "/adminKatalog")
public class KatalogController {

	@Autowired
	PisacRepository pisacRepository;

	@Autowired
	JezikRepository jezikRepository;

	@Autowired
	KategorijaRepository kategorijaRepository;

	@Autowired
	KnjigaRepository knjigaRepository;

//	====================== Model Atributi ======================

//	====================== Pisac ======================

	@ModelAttribute(value = "pisac")
	public void getPisac(Model model) {
		Pisac pisac = new Pisac();
		model.addAttribute("pisac", pisac);
	}

	@ModelAttribute(value = "sviPisci")
	public void getSviPisci(Model model) {
		List<Pisac> sviPisci = pisacRepository.findAll();
		model.addAttribute("sviPisci", sviPisci);
	}

//	====================== Jezik ======================

	@ModelAttribute(value = "jezik")
	public void getJezik(Model model) {
		Jezik jezik = new Jezik();
		model.addAttribute("jezik", jezik);
	}

	@ModelAttribute(value = "sviJezici")
	public void getSviJezici(Model model) {
		List<Jezik> sviJezici = jezikRepository.findAll();
		model.addAttribute("sviJezici", sviJezici);
	}

//	====================== Kategorija ======================

	@ModelAttribute(value = "kategorija")
	public void getKategorija(Model model) {
		Kategorija kategorija = new Kategorija();
		model.addAttribute("kategorija", kategorija);
	}

	@ModelAttribute(value = "sveKategorije")
	public void getSveKategorije(Model model) {
		List<Kategorija> sveKategorije = kategorijaRepository.findAll();
		model.addAttribute("sveKategorije", sveKategorije);
	}

//	====================== Knjiga ======================

	@ModelAttribute(value = "knjigaImg")
	public void getKnjiga(Model model) {
		KnjigaImage knjigaImage = new KnjigaImage();
		model.addAttribute("knjigaImg", knjigaImage);
	}

	@ModelAttribute(value = "sveKnjige")
	public void getSveKnjige(Model model) {
		List<Knjiga> sveKnjige = knjigaRepository.findAll();
		model.addAttribute("sveKnjige", sveKnjige);
	}

//	====================== Model Atributi ======================

//	====================== Request Metode ======================

// 	====================== Pisac crud ======================

	@RequestMapping(value = "/dodajPisca", method = RequestMethod.GET)
	public String dodajPisca() {
		return "knjigaUnos/unosPisca";
	}

	@RequestMapping(value = "/savePisac", method = RequestMethod.POST)
	public String savePisac(@ModelAttribute("pisac") Pisac pisac, HttpServletRequest request) {
		Pisac p = pisacRepository.save(pisac);
		String poruka = null;
		if (p != null) {
			poruka = "Uspesno sacuvano.";
		} else {
			poruka = "Greska! Pisac nije sacuvan.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("p", p);
		return "knjigaUnos/unosPisca";
	}

	@RequestMapping(value = "/obrisiPisca", method = RequestMethod.GET)
	public String obrisiPisca() {
		return "knjigaUnos/brisanjePisca";
	}

	@RequestMapping(value = "/deletePisac", method = RequestMethod.GET)
	public String deletePisac(@RequestParam(value = "idPisac") int idPisac, HttpServletRequest request, Model model) {
		pisacRepository.deleteById(idPisac);
		Optional<Pisac> opp = pisacRepository.findById(idPisac);

		Pisac p = null;
		String poruka = null;
		if (!opp.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			p = opp.get();
		}

		request.setAttribute("poruka", poruka);
		request.setAttribute("p", p);
		getSviPisci(model);
		return "knjigaUnos/brisanjePisca";
	}

//	====================== Pisac crud ======================

// 	====================== Jezik crud ======================

	@RequestMapping(value = "/dodajJezik", method = RequestMethod.GET)
	public String dodajJezik() {
		return "knjigaUnos/unosJezika";
	}

	@RequestMapping(value = "/saveJezik", method = RequestMethod.POST)
	public String savePisac(@ModelAttribute("jezik") Jezik jezik, HttpServletRequest request) {
		Jezik j = jezikRepository.save(jezik);
		String poruka = null;
		if (j != null) {
			poruka = "Uspesno sacuvano.";
		} else {
			poruka = "Greska! Pisac nije sacuvan.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("j", j);
		return "knjigaUnos/unosJezika";
	}

	@RequestMapping(value = "/obrisiJezik", method = RequestMethod.GET)
	public String obrisiJezik() {
		return "knjigaUnos/brisanjeJezika";
	}

	@RequestMapping(value = "/deleteJezik", method = RequestMethod.GET)
	public String deleteJezik(@RequestParam(value = "idJezik") int idJezik, HttpServletRequest request, Model model) {
		jezikRepository.deleteById(idJezik);
		Optional<Jezik> opj = jezikRepository.findById(idJezik);

		Jezik j = null;
		String poruka = null;
		if (!opj.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			j = opj.get();
		}

		request.setAttribute("poruka", poruka);
		request.setAttribute("j", j);
		getSviJezici(model);
		return "knjigaUnos/brisanjeJezika";
	}

//	====================== Jezik crud ======================

//	====================== Kategorija crud ======================

	@RequestMapping(value = "/dodajKategoriju", method = RequestMethod.GET)
	public String dodajKategoriju() {
		return "knjigaUnos/unosKategorije";
	}

	@RequestMapping(value = "/saveKategorija", method = RequestMethod.POST)
	public String savePisac(@ModelAttribute("kategorija") Kategorija kategorija, HttpServletRequest request) {
		Kategorija k = kategorijaRepository.save(kategorija);
		String poruka = null;
		if (k != null) {
			poruka = "Uspesno sacuvano.";
		} else {
			poruka = "Greska! Pisac nije sacuvan.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("k", k);
		return "knjigaUnos/unosKategorije";
	}

	@RequestMapping(value = "/obrisiKategoriju", method = RequestMethod.GET)
	public String obrisiKategoriju() {
		return "knjigaUnos/brisanjeKategorije";
	}

	@RequestMapping(value = "/deleteKategorija", method = RequestMethod.GET)
	public String deleteKategorija(@RequestParam(value = "idKategorija") int idKategorija, HttpServletRequest request,
			Model model) {
		kategorijaRepository.deleteById(idKategorija);
		Optional<Kategorija> opk = kategorijaRepository.findById(idKategorija);

		Kategorija k = null;
		String poruka = null;
		if (!opk.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			k = opk.get();
		}

		request.setAttribute("poruka", poruka);
		request.setAttribute("k", k);
		getSveKategorije(model);
		return "knjigaUnos/brisanjeKategorije";
	}

//	====================== Kategorija crud ======================

//	====================== Knjiga crud ======================

	@RequestMapping(value = "/dodajKnjigu", method = RequestMethod.GET)
	public String dodajKnjigu(Model model) {
		getSveKategorije(model);
		getSviPisci(model);
		getSviJezici(model);
		return "knjigaUnos/unosKnjige";
	}

	@RequestMapping(value = "/saveKnjiga", method = RequestMethod.POST)
	public String saveKnjiga(Model m, @ModelAttribute("knjigaImg") KnjigaImage knjigaImage,
			HttpServletRequest request) {
		MultipartFile file = knjigaImage.getSlika();
		byte[] bs = null;

		System.out.println("Pre ifa");

		if (file != null) {
			String fileName = file.getOriginalFilename();
			String filePath;
			try {
				filePath = System.getProperty("user.dir");
				System.out.println("Putanja je " + filePath);
				File imageFile = new File(filePath, fileName);

				file.transferTo(imageFile);
				bs = Files.readAllBytes(imageFile.toPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Posle ifa");

		Knjiga knjiga = new Knjiga();
		knjiga.setNaziv(knjigaImage.getNaziv());
		knjiga.setGodinaIzdanja(knjigaImage.getGodinaIzdanja());
		knjiga.setIzdavac(knjigaImage.getIzdavac());
		knjiga.setOpis(knjigaImage.getOpis());

		knjiga.setKategorijas(knjigaImage.getKategorijas());
		List<Kategorija> kategorijas = knjiga.getKategorijas();
		for (Kategorija kategorija : kategorijas) {
			kategorija.getKnjigas().add(knjiga);
		}

		knjigaImage.getPisac().addKnjiga(knjiga);
		knjigaImage.getJezik().addKnjiga(knjiga);

		knjiga.setSlika(bs);

		System.out.println("Pre save");

		Knjiga k = knjigaRepository.save(knjiga);

		System.out.println("Posle save");

		String poruka = null;
		if (k != null) {
			poruka = "Uspesno sacuvano.";
		} else {
			poruka = "Greska! Pisac nije sacuvan.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("k", k);

		return "knjigaUnos/unosKnjige";
	}


	@RequestMapping(value = "/deleteKnjiga", method = RequestMethod.GET)
	public String deleteKnjiga(@RequestParam(value = "idKnjiga") int idKnjiga, HttpServletRequest request,
			RedirectAttributes model) {
		knjigaRepository.deleteById(idKnjiga);
		Optional<Knjiga> opk = knjigaRepository.findById(idKnjiga);

		Knjiga k = null;
		String poruka = null;
		if (!opk.isPresent()) {
			poruka = "Uspesno brisanje.";
		} else {
			poruka = "Greska!";
			k = opk.get();
		}

		model.addFlashAttribute("poruka", poruka);
		model.addFlashAttribute("k", k);
		getSveKnjige(model);
		return "redirect:/pretraga/pretraziKnjige";
	}

//	====================== Knjiga crud ======================

}
