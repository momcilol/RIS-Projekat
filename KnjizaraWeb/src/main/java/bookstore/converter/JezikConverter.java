package bookstore.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bookstore.repository.JezikRepository;
import model.Jezik;

@Component
public class JezikConverter implements Converter<String, Jezik> {

	JezikRepository jr;
	
	
	public JezikConverter(JezikRepository jr) {
		this.jr = jr;
	}


	@Override
	public Jezik convert(String source) {
		int id = -1;
		try {
			id = Integer.parseInt(source);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Jezik.class), id, null);
		}
		Jezik j = jr.findById(id).get();
		return j;
	}

}
