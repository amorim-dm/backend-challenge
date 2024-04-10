package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		this.createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		TaskDTO taskDTO = TaskDTO.create().setTitle("TEST").setDescription("TEST DESC");
		Task task = createTaskService.execute(taskDTO);
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		Assert.assertEquals(retrieveAllTasksService.execute(), tasks);
	}

}