package bookstore.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date> {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date convert(String source) {
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
