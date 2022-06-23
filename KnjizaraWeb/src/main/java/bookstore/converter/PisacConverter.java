package bookstore.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bookstore.repository.PisacRepository;
import model.Pisac;

@Component
public class PisacConverter implements Converter<String, Pisac> {

	PisacRepository pr;

	public PisacConverter(PisacRepository pr) {
		this.pr = pr;
	}

	@Override
	public Pisac convert(String source) {
		int id = -1;
		try {
			id = Integer.parseInt(source);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Pisac.class), id, null);
		}
		Pisac p = pr.findById(id).get();
		return p;
	}

}
