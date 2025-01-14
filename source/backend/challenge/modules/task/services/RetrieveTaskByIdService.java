package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;


//There was no service applied for that METHOD
@Singleton
public class RetrieveTaskByIdService implements IRetrieveTaskByIdService {

	private final ITaskRepository taskRepository;

	@Inject
	public RetrieveTaskByIdService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task execute(Long taskId) {
		return this.taskRepository.index(taskId);
	}

}
