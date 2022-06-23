package bookstore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Zaposleni;

@Repository
@Transactional
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer>, ZaposleniReportRepository {

	
}
