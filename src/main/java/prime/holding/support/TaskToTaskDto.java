package prime.holding.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import prime.holding.model.Task;
import prime.holding.web.dto.TaskDto;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskToTaskDto implements Converter<Task, TaskDto> {
    @Override
    public TaskDto convert(Task source) {
        TaskDto dto = new TaskDto();
        dto.setId(source.getId());
        dto.setTitle(source.getTitle());
        dto.setDescription(source.getDescription());
        dto.setEmployeeId(source.getEmployee().getId());
        dto.setEmployeeName(source.getEmployee().getFullName());
        dto.setDueDate(source.getDueDate().toString());

        return dto;
    }

    public List<TaskDto> convertAll(List<Task> tasks){
    	List<TaskDto> dtos = new ArrayList<>();
    	for(Task task: tasks){
    		dtos.add(convert(task));
    	}
    	return dtos;
    }

}
