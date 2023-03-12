package prime.holding.service;

import java.util.List;
import prime.holding.model.Employee;

public interface EmployeeService {
    
    Employee findById(Long id);

    List<Employee> findAll();

    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee delete(Long id);

    List<Employee> topFiveInPastMonth();
   
}
