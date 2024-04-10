package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private ICreateTaskService createTaskService;
	private ITaskRepository taskRepository;

	@Before
	public void init() {
		this.taskRepository = new TaskRepository();
		this.retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		this.createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		TaskDTO taskDTO = TaskDTO.create().setTitle("TEST").setDescription("TEST DESC");
		Task task = createTaskService.execute(taskDTO);
		Assert.assertEquals(retrieveTaskByIdService.execute(task.getId()), task);
	}

}
