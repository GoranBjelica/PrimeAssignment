package prime.holding.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.model.WorkPlace;
import prime.holding.web.dto.WorkPlaceDto;

@Component
public class WokrPlaceToWorkPlaceDto implements Converter<WorkPlace, WorkPlaceDto> {

	@Override
	public WorkPlaceDto convert(WorkPlace source) {

		WorkPlaceDto dto = new WorkPlaceDto();
		dto.setId(source.getId());
		dto.setCountry(source.getCountry().toString());
		dto.setCity(source.getCity());
		return dto;
	}

	public List<WorkPlaceDto> convertAll(List<WorkPlace> workPlaces){
		List<WorkPlaceDto> dtos = new ArrayList<WorkPlaceDto>();
		for(WorkPlace wp : workPlaces) {
			dtos.add(convert(wp));
		}
		return dtos;
	}

}
