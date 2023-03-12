package prime.holding.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.model.Task;
import prime.holding.service.EmployeeService;
import prime.holding.web.dto.TaskDto;

@Component
public class TaskDtoToTask implements Converter<TaskDto, Task> {
	
	@Autowired
	EmployeeService employeeService;

	@Override
	public Task convert(TaskDto dto) {
		Task task = new Task();
		task.setDescription(dto.getDescription());
		task.setTitle(dto.getTitle());
		task.setEmployee(employeeService.findById(dto.getEmployeeId()));
		task.setDueDate(getLocalDate(dto.getDueDate()));
		
		return task;
	}

	private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
