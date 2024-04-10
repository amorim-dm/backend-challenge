package backend.challenge.modules.task.services;

import javax.inject.Inject;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

public class UpdateTaskProgressService implements IUpdateTaskProgressService{

  private final ITaskRepository taskRepository;
  private final IRetrieveTaskByIdService retrieveTaskByIdService;

  @Inject
	public UpdateTaskProgressService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
    this.retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

  @Override
  public Task execute(TaskProgressDTO taskProgressDTO) {
    Task task = retrieveTaskByIdService.execute(taskProgressDTO.getId());
    task.setProgress(taskProgressDTO.getProgress() > 100 ? 100 : taskProgressDTO.getProgress());
    if(task.getProgress() >= 100) {
      task.setStatus(TaskStatus.COMPLETE);
    }
    return taskRepository.update(task);
  }
  
}
