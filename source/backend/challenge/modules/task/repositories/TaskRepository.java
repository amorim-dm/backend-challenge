package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import java.util.Date;

@Singleton
public class TaskRepository implements ITaskRepository {

	private Map<Long, Task> tasks;
	private Long currentId;

	public TaskRepository() {
		this.tasks = new HashMap<Long, Task>();
		this.currentId = 1L;
	}

	@Override
	public Task index(final Long taskId) {
		return this.tasks.get(taskId);
	}

	@Override
	public List<Task> show() {
		return this.tasks.values().stream().toList();
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task newTask = new Task();
		newTask.setId(this.currentId);
		newTask.setTitle(taskDTO.getTitle());
		newTask.setDescription(taskDTO.getDescription());
		newTask.setProgress(0);
		newTask.setStatus(TaskStatus.PROGRESS);
		newTask.setCreatedAt(Date.from(Instant.now()));
		this.tasks.put(this.currentId, newTask);
		this.currentId++;
		return newTask;
	}

	@Override
	public Task update(final Task task) {;
		return this.tasks.replace(task.getId(), task);
	}

	@Override
	public void delete(final Long taskId) {
		Task task = this.index(taskId);
		this.tasks.remove(taskId, task);
	}
}
