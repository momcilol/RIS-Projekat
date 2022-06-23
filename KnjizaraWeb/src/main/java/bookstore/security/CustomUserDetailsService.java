package bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookstore.repository.KorisnikRepository;
import model.Korisnik;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findByUsername(username);
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		userDetailsImpl.setUsername(korisnik.getUsername());
		userDetailsImpl.setPassword(korisnik.getPassword());
		userDetailsImpl.setRole(korisnik.getUloga());
		return userDetailsImpl;
	}

}
