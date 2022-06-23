package bookstore.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bookstore.repository.KorisnikRepository;
import bookstore.repository.UlogaRepository;
import bookstore.repository.ZaposleniRepository;
import model.Korisnik;
import model.Uloga;
import model.Zaposleni;

@Controller
@ControllerAdvice
@RequestMapping(value = "auth")
public class LoginController {

	@Autowired
	UlogaRepository ulogaRepository;

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	ZaposleniRepository zaposleniRepository;

	@ModelAttribute
	public void getRoles(Model model) {
		List<Uloga> roles = ulogaRepository.findAll();
		model.addAttribute("roles", roles);
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public String registerUser(Model model) {
		Korisnik k = new Korisnik();
		model.addAttribute("korisnik", k);
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("korisnik") Korisnik k) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		k.setPassword(passwordEncoder.encode(k.getPassword()));
		Uloga u = k.getUloga();
		u.addKorisnik(k);
		k = korisnikRepository.saveAndFlush(k);

		System.out.println("Id korisnika je " + k.getIdKorisnik());

		if (k.getUloga().getIdUloga() != 3) {
			Zaposleni zaposleni = new Zaposleni();
			zaposleni.setKorisnik_idKorisnik(k.getIdKorisnik());
			zaposleni.setDatumZaposlenja(new Date());
			zaposleni.setKorisnik(k);
			k.setZaposleni(zaposleni);
			zaposleniRepository.save(zaposleni);
		}

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/auth/loginPage";
	}

	@RequestMapping(value = "/pocetna", method = RequestMethod.GET)
	public String getPocetna() {
		return "pocetna";
	}

}
