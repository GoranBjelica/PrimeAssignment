package prime.holding.service;

import java.util.List;
import prime.holding.model.WorkPlace;

public interface WorkPlaceService {
	
	WorkPlace findById(Long id);
	
	List<WorkPlace> findAll();
	
	WorkPlace save (WorkPlace workPlace);
	
	void delete (Long id);
	
	WorkPlace topCity();
	
}
