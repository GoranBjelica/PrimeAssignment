package prime.holding.service;

import java.util.List;
import prime.holding.model.Task;

public interface TaskService {

    List<Task> findAll();
    Task findOne(Long id);
    Task save(Task task);
    Task update(Task task);
    Task delete(long taskId);
    List<Task> findAllInPastMonth();
    
}
