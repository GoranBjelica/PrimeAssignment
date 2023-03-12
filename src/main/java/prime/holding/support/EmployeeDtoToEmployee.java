package prime.holding.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.model.Employee;
import prime.holding.service.WorkPlaceService;
import prime.holding.web.dto.EmployeeDTO;


@Component
public class EmployeeDtoToEmployee implements Converter<EmployeeDTO, Employee> {

	@Autowired
	private WorkPlaceService workPlaceService;
	
	@Override
	public Employee convert(EmployeeDTO dto) {
		Employee employee = new Employee();
		employee.setFullName(dto.getFullName());
		employee.setEmail(dto.geteMail());
		employee.setPhoneNumber(dto.getPhoneNumber());
		employee.setSalary(dto.getSolary());
		employee.setDateOfBirth(getLocalDate(dto.getDateOfBirth()));
		if(workPlaceService.findById(dto.getAddressId()) == null){
			employee.setWorkPlace(workPlaceService.findById(1L));
		}else {
			employee.setWorkPlace(workPlaceService.findById(dto.getAddressId()));
		}
		return employee;
	}
	
	private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
	
}
