package prime.holding.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prime.holding.model.WorkPlace;
import prime.holding.repository.WorkPlaceRepository;
import prime.holding.service.WorkPlaceService;

@Service
public class JPAWorkPlaceService implements WorkPlaceService {
	
	@Autowired
	private WorkPlaceRepository workPlaceRepository;

	@Override
	public WorkPlace findById(Long id) {
		Optional<WorkPlace> optional = workPlaceRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<WorkPlace> findAll() {
		List<WorkPlace> workplaces = workPlaceRepository.findAll();
		return workplaces;
	}

	@Override
	public WorkPlace save(WorkPlace workPlace) {
		return workPlaceRepository.save(workPlace);
	}

	@Override
	public void delete(Long id) {
	workPlaceRepository.deleteById(id);
	}

	@Override
	public WorkPlace topCity() {
		List<WorkPlace> allWorkPlaces = workPlaceRepository.findAll();
		
		WorkPlace topWorkPlace = allWorkPlaces.stream().
											   max(Comparator.comparing(WorkPlace :: thisMonthTasksByCity )).orElse(null);
		return topWorkPlace;
	}
	
}
