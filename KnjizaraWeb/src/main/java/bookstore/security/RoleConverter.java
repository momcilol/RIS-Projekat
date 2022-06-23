package bookstore.security;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import bookstore.repository.UlogaRepository;
import model.Uloga;

public class RoleConverter implements Converter<String, Uloga> {

	UlogaRepository ulogaRepository;

	public RoleConverter(UlogaRepository ulogaRepository) {
		this.ulogaRepository = ulogaRepository;
	}

	@Override
	public Uloga convert(String source) {
		int idRole = -1;
		try {
			idRole = Integer.parseInt(source);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Uloga.class), idRole, null);
		}
		Uloga role = ulogaRepository.getById(idRole);
		return role;
	}

}
