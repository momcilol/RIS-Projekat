package bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Knjiga;

public interface KnjigaRepository extends JpaRepository<Knjiga, Integer> {

}
