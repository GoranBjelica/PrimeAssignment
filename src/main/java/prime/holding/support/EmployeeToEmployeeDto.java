package prime.holding.support;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.model.Employee;
import prime.holding.web.dto.EmployeeDTO;

@Component
public class EmployeeToEmployeeDto implements Converter<Employee, EmployeeDTO> {

	@Override
	public EmployeeDTO convert(Employee source) {

		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(source.getId());
		dto.setFullName(source.getFullName());
		dto.seteMail(source.getEmail());
		dto.setPhoneNumber(source.getPhoneNumber());
		dto.setSolary(source.getSalary());
		dto.setDateOfBirth(source.getDateOfBirth().toString());
		dto.setCountry(source.getWorkPlace().getCountry().toString());
		dto.setCity(source.getWorkPlace().getCity());
		dto.setAddressId(source.getWorkPlace().getId());

		return dto;
	}
	public List<EmployeeDTO> convertAll(List<Employee> employees){
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
		for(Employee emp: employees) {
			dtos.add(convert(emp));
		}
		return dtos;	
	}

}
