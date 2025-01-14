package backend.challenge.modules.task.services;

import javax.inject.Inject;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

public class UpdateTaskService implements IUpdateTaskService {

  private final ITaskRepository taskRepository;

  @Inject
  public UpdateTaskService(final ITaskRepository taskRepository){
    this.taskRepository = taskRepository;
  }

  @Override
  public Task execute(Task task) {
    return this.taskRepository.update(task);
  }
  
}
