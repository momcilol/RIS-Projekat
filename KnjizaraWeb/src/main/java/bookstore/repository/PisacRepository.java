package bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Pisac;

public interface PisacRepository extends JpaRepository<Pisac, Integer> {
	
}
