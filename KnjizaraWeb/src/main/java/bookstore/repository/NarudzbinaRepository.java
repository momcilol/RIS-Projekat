package bookstore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Narudzbina;

public interface NarudzbinaRepository extends JpaRepository<Narudzbina, Integer> {

	List<Narudzbina> findByDatumBetweenOrderByKnjiga(Date datumOd, Date datumDo);
}
