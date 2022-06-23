package bookstore.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import model.Zaposleni;

@Repository
public class ZaposleniRepositoryImpl implements ZaposleniReportRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Zaposleni> getSviZaposleniOrderByStazZaposlenjePlata(Map<String, String> ordering) {
		

		StringBuilder sb = new StringBuilder("SELECT z FROM Zaposleni z ");
		
		if (!ordering.isEmpty()) {
			sb.append("ORDER BY ");
			
			for (Map.Entry<String, String> entry : ordering.entrySet()) {
				String kolona = entry.getKey();
				String poredak = entry.getValue();
				
				sb.append(kolona + " " + poredak + ", ");
			}
		}
		
		String query = sb.toString();
		
		if (query.endsWith(", ")) {
			query = query.substring(0, query.length() - 2);
		}
		
		System.out.println(query);
	
		List<Zaposleni> zaposlenis = em.createQuery(query, Zaposleni.class).getResultList();
		
		return zaposlenis;
	}

	
	
}
