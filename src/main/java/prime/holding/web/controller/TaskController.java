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
import prime.holding.model.Task;
import prime.holding.service.TaskService;
import prime.holding.support.TaskDtoToTask;
import prime.holding.support.TaskDtoToTaskUpdate;
import prime.holding.support.TaskToTaskDto;
import prime.holding.web.dto.TaskDto;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/tasks")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskToTaskDto toDto;
    
    @Autowired
    private TaskDtoToTask toTask;
    
    @Autowired
    private TaskDtoToTaskUpdate toTaskUpdate;

    
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(){

        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(toDto.convertAll(tasks), HttpStatus.OK);
    }
    
    @GetMapping (value = "/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id){
    	
    	Task task = taskService.findOne(id);  	
    	if(task == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<>(toDto.convert(task), HttpStatus.OK);	
    }
    
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> add(@Valid @RequestBody TaskDto taskDto){
    	
    	Task task = toTask.convert(taskDto);
    	Task savedTask = taskService.save(task);

    	return new ResponseEntity<>(toDto.convert(savedTask) ,HttpStatus.OK);	
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto){
    	
    	if(!id.equals(taskDto.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	if(taskService.findOne(id) == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Task task = toTaskUpdate.convert(taskDto);
    	Task updatedTask = taskService.update(task);
    	return new ResponseEntity<>(toDto.convert(updatedTask), HttpStatus.OK);     	
    }
    
    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
    	Task deletedTask = taskService.delete(id);
    	if(deletedTask != null) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping(value = "/tasksFromLastMonth")
    public ResponseEntity<List<TaskDto>> tasksFromLastMonth(){
    	List<Task> tasks = taskService.findAllInPastMonth();
    	
    	return new ResponseEntity<>(toDto.convertAll(tasks), HttpStatus.OK);	
    }
    
} 
    