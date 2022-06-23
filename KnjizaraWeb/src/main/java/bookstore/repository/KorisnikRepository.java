package bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	Korisnik findByUsername(String username);
	
	@Query("SELECT k "
		 + "FROM Korisnik k "
		 + "WHERE k.uloga.naziv like 'Zaposleni'")
	List<Korisnik> findZaposleniKorisniks();
}
