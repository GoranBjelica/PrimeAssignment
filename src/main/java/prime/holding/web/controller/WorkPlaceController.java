package prime.holding.web.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prime.holding.enumeration.Country;
import prime.holding.model.WorkPlace;
import prime.holding.service.WorkPlaceService;
import prime.holding.support.WokrPlaceToWorkPlaceDto;
import prime.holding.support.WorkPlaceDtoToWorkPlace;
import prime.holding.web.dto.WorkPlaceDto;

@RestController
@RequestMapping(value = "api/workplaces", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class WorkPlaceController {

	@Autowired
	private WorkPlaceService workPlaceService;

	@Autowired
	private WokrPlaceToWorkPlaceDto toDto;

	@Autowired
	private WorkPlaceDtoToWorkPlace toWorkPlace;


	@GetMapping
	public ResponseEntity<List<WorkPlaceDto>> getAll(){

		List<WorkPlace> workPlaces = workPlaceService.findAll();
		return new ResponseEntity<>(toDto.convertAll(workPlaces), HttpStatus.OK);	
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkPlaceDto> getOne(@PathVariable Long id){
		WorkPlace workPlace = workPlaceService.findById(id);
		if(workPlace == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(toDto.convert(workPlace), HttpStatus.OK);	
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkPlaceDto> add(@Valid @RequestBody WorkPlaceDto workPlaceDto){

		if(!workPlaceDto.getCountry().equals(Country.SERBIA.toString()) &&
				!workPlaceDto.getCountry().equals(Country.BULGARIA.toString())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		WorkPlace workPlace = toWorkPlace.convert(workPlaceDto);
		WorkPlace savedWorkPlace = workPlaceService.save(workPlace);

		return new ResponseEntity<>(toDto.convert(savedWorkPlace), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		WorkPlace workplace = workPlaceService.findById(id);
		if(workplace == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(!workplace.getEmployees().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		workPlaceService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/employeesPerCity/{id}")
	public ResponseEntity<String> employeesPerCity(@PathVariable Long id){
		WorkPlace workPlace = workPlaceService.findById(id);
		if(workPlace == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String answer = "There is " + workPlace.getEmployees().size() + " employees in " + workPlace.getCity();
		
		return new ResponseEntity<>(answer, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/topcity")
	public ResponseEntity<WorkPlaceDto> cityWithTheMostTaskInThisMonth(){
		WorkPlace topWorkPlace = workPlaceService.topCity();
		System.out.println(topWorkPlace.getCity() + "-  tasks: " + topWorkPlace.thisMonthTasksByCity());           //this is just to write in console city name and tasks number
		
		return new ResponseEntity<>(toDto.convert(topWorkPlace), HttpStatus.OK);
	}
	
}
