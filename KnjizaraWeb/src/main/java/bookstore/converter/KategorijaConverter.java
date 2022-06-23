package bookstore.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bookstore.repository.KategorijaRepository;
import model.Kategorija;

@Component
public class KategorijaConverter implements Converter<String, Kategorija> {

	KategorijaRepository kr;

	public KategorijaConverter(KategorijaRepository kr) {
		this.kr = kr;
	}

	@Override
	public Kategorija convert(String source) {
		int id = -1;
		try {
			id = Integer.parseInt(source);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Kategorija.class), id, null);
		}
		Kategorija k = kr.findById(id).get();
		return k;
	}

}
