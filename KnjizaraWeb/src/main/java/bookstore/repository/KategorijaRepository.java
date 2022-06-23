package bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {

}
