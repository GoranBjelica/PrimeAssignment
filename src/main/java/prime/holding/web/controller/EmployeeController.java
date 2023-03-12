package prime.holding.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prime.holding.model.Employee;
import prime.holding.service.EmployeeService;
import prime.holding.support.EmployeeDtoToEmployee;
import prime.holding.support.EmployeeDtoToEmployeeUpdate;
import prime.holding.support.EmployeeToEmployeeDto;
import prime.holding.web.dto.EmployeeDTO;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeToEmployeeDto toDto;
    
    @Autowired
    private EmployeeDtoToEmployee toEmployee;
    
    @Autowired
    private EmployeeDtoToEmployeeUpdate toEmployeeUpdate;
    
  
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll(){

    	List<Employee> employees = employeeService.findAll();

    	return new ResponseEntity<>(toDto.convertAll(employees), HttpStatus.OK);
    }
    
    @GetMapping (value = "/{id}")
    public ResponseEntity<EmployeeDTO> getOne(@PathVariable Long id){
    	
    	Employee employee = employeeService.findById(id);
    	if(employee == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(toDto.convert(employee), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> add(@Valid @RequestBody EmployeeDTO employeeDTO){
        	
    	Employee employee = toEmployee.convert(employeeDTO);
    	Employee savedEmployee = employeeService.save(employee);
    	
    	return new ResponseEntity<>(toDto.convert(savedEmployee), HttpStatus.OK); 
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> update (@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO){
    	if(!id.equals(employeeDTO.getId())) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	if(employeeService.findById(id) == null) {
    		return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
    	}
    	Employee employee = toEmployeeUpdate.convert(employeeDTO);
    	Employee updatedEmployee = employeeService.update(employee);
    	return new ResponseEntity<>(toDto.convert(updatedEmployee), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	Employee deleteEmployee = employeeService.delete(id);
    	if(deleteEmployee != null) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping(value = "/best5")
	public ResponseEntity<List<EmployeeDTO>> topFiveEmployeesInLastMonth(){
		
	List<Employee> topFive = employeeService.topFiveInPastMonth();
	for(Employee e : topFive) {
		System.out.println(e.getFullName() + " " + e.tasksInPasMonthForEmployee()); //this is just to write in console top 5 employees in last month and how many task they had
	}
	return new ResponseEntity<>(toDto.convertAll(topFive), HttpStatus.OK);
	}
     
}
