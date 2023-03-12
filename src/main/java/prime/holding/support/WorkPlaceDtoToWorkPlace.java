package prime.holding.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.enumeration.Country;
import prime.holding.model.WorkPlace;
import prime.holding.web.dto.WorkPlaceDto;

@Component
public class WorkPlaceDtoToWorkPlace implements Converter<WorkPlaceDto, WorkPlace> {

	@Override
	public WorkPlace convert(WorkPlaceDto source) {
		WorkPlace workPlace = new WorkPlace();
		workPlace.setCountry(Country.valueOf(source.getCountry()));
		workPlace.setCity(source.getCity());
		
		return workPlace;
	}

}
