package prime.holding.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prime.holding.model.Employee;
import prime.holding.repository.EmployeeRepository;
import prime.holding.service.EmployeeService;

@Service
public class JPAEmployeeService implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee findById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()){
			return employee.get();
		}
		return null;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee delete(Long id) {
		Optional<Employee> employee= employeeRepository.findById(id);
		if(employee.isPresent()) {
			employee.get().getTasks().forEach(System.out::println);
			employee.get().getWorkPlace().getEmployees().remove(employee.get());
			employee.get().getTasks().clear();
			employee.get().getTasks().forEach(System.out::println);
			employeeRepository.deleteById(id);
			return employee.get();
		} else {return null;}
	}

	@Override
	public List<Employee> topFiveInPastMonth() {
		
		List<Employee> allEmployees = employeeRepository.findAll();
		List<Employee>topFive =  allEmployees.stream().
					 sorted(Comparator.comparing(Employee :: tasksInPasMonthForEmployee).reversed()).
					 limit(5).
					 collect(Collectors.toList());
		
		
		return topFive;
	}
}
