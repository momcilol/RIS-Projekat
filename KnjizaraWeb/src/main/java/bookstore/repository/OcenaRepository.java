package bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Ocena;
import model.OcenaPK;

public interface OcenaRepository extends JpaRepository<Ocena, OcenaPK> {

	@Query("SELECT o " 
		 + "FROM Ocena o " 
		 + "WHERE o.knjiga.idKnjiga = :id and o.korisnik.username = :user")
	public Ocena findByUsernameAndKnjiga(@Param("id") int idKnjiga, @Param("user") String username);

	@Query("SELECT o " 
		 + "FROM Ocena o " 
		 + "WHERE o.knjiga.idKnjiga = :id")
	public List<Ocena> findByIdKnjiga(@Param("id") int idKnjiga);
}
