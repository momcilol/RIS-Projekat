package bookstore.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bookstore.converter.DateConverter;
import bookstore.repository.NarudzbinaRepository;
import bookstore.repository.ZaposleniRepository;
import model.Narudzbina;
import model.Zaposleni;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
	
	@Autowired
	ZaposleniRepository zaposleniRepository;
	
	@Autowired
	NarudzbinaRepository narudzbinaRepository;

	@RequestMapping(value = "/allReports", method = RequestMethod.GET)
	public String allReports() {
		return "izvestaji/sviIzvestaji";
	}
	
//	===================== Zaposleni ==========================
	
	@RequestMapping(value = "/sviZaposleni", method = RequestMethod.GET)
	public String sviZaposleni() {
		return "izvestaji/izvestajZaposleni";
	}
	
	@RequestMapping(value = "/getSviZaposleniReport.pdf", method = RequestMethod.GET)
	public void getSviZaposleniReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> ordering = new HashMap<>();
		
		if (request.getParameter("sCheck") != null) {
			ordering.put("radniStaz", request.getParameter("radniStaz"));
		}
		
		if (request.getParameter("dCheck") != null) {
			ordering.put("datumZaposlenja", request.getParameter("datumZaposlenja"));
		}
		
		if (request.getParameter("pCheck") != null) {
			ordering.put("plata", request.getParameter("plata"));
		}
		
		List<Zaposleni> zaposlenis = zaposleniRepository.getSviZaposleniOrderByStazZaposlenjePlata(ordering);
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(zaposlenis);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/Zaposleni.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imeKnjizare", "Plavi krug");
		params.put("datum", new Date());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
	
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=SviZaposleni.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
		
	}
	
//	===================== Zaposleni ==========================

//	===================== Narudzbine =========================
	
	@RequestMapping(value = "/narudzbine", method = RequestMethod.GET)
	public String narudzbine() {
		return "izvestaji/izvestajNarudzbine";
	}
	
	@RequestMapping(value = "/getNarudzbineKnjiga.pdf", method = RequestMethod.GET)
	public void getNarudzbineKnjiga(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateConverter dateConverter = new DateConverter();
		
		Date datumOd =  dateConverter.convert(request.getParameter("datumOd"));
		Date datumDo =  dateConverter.convert(request.getParameter("datumDo"));
		
		List<Narudzbina> narudzbinas = narudzbinaRepository.findByDatumBetweenOrderByKnjiga(datumOd, datumDo);
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(narudzbinas);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/NarudzbineKnjiga.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imeKnjizare", "Plavi krug");
		params.put("datumOd", datumOd);
		params.put("datumDo", datumDo);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
	
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=NarudzbineKnjiga.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
		
	}

}
