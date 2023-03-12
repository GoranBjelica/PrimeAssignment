package prime.holding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prime.holding.model.Task;
import prime.holding.repository.TaskRepository;
import prime.holding.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaTaskService implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		List<Task> tasks = taskRepository.findAll();
		return tasks;
	}

	@Override
	public Task findOne(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isPresent()) {
			return task.get();
		}
		return null;
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task update(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task delete(long taskId) {
		Optional<Task> task = taskRepository.findById(taskId);
		if(task.isPresent()) {
			task.get().getEmployee().getTasks().remove(task.get());
			taskRepository.deleteById(taskId);
			return task.get();
		}
		return null;
	}

	@Override
	public List<Task> findAllInPastMonth() {
		List<Task> allTasks = findAll();
		LocalDate currentDate = LocalDate.now();
		List<Task> taskInLastMonth = allTasks.stream().
				filter(t -> t.getDueDate().getMonth() == currentDate.getMonth().minus(1)).
				collect(Collectors.toList());
		return taskInLastMonth;
	}

}
