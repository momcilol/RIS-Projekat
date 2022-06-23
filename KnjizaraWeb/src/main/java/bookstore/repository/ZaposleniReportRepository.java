package bookstore.repository;

import java.util.List;
import java.util.Map;

import model.Zaposleni;

public interface ZaposleniReportRepository {
	
	public List<Zaposleni> getSviZaposleniOrderByStazZaposlenjePlata(Map<String, String> ordering); 

}
