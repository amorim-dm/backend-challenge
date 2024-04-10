package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yaml.snakeyaml.events.Event.ID;

@RunWith(KikahaRunner.class)
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private IRetrieveAllTasksService retrieveAllTasksService;
	private IDeleteTaskService deleteTaskService;
	private TaskController taskController;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		this.createTaskService = new CreateTaskService(taskRepository);
		this.deleteTaskService = new DeleteTaskService(taskRepository);
		this.updateTaskService = new UpdateTaskService(taskRepository);
		this.retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		this.retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		this.taskController = new TaskController(createTaskService, deleteTaskService, retrieveAllTasksService, retrieveTaskByIdService, updateTaskService);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		Task task = this.createTaskService.execute(TaskDTO.create().setTitle("TEST").setDescription("TEST DESC"));
		Task task2 = this.createTaskService.execute(TaskDTO.create().setTitle("TEST 2").setDescription("TEST DESC 2"));
		task.setDescription("TEST DESC #2");
		task.setTitle("TEST #2");
		this.taskController.update(task.getId(), task2);
		Assert.assertEquals(task.getTitle(), task2.getTitle());
    Assert.assertEquals(task.getDescription(), task2.getDescription());
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		Task task2 = this.createTaskService.execute(TaskDTO.create().setTitle("TEST 2").setDescription("TEST DESC 2"));
		Response response = this.taskController.update(9L, task2);
		Assert.assertEquals(response.statusCode(), 400);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		Task task = this.createTaskService.execute(TaskDTO.create().setTitle("TEST").setDescription("TEST DESC"));
		Task task2 = this.createTaskService.execute(TaskDTO.create().setTitle("TEST 2").setDescription("TEST DESC 2"));
		task.setDescription("TEST DESC #2");
		task.setTitle("TEST #2");
		task.setStatus(TaskStatus.COMPLETE);
		this.taskController.update(task.getId(), task2);
		Assert.assertNotEquals(task.getStatus(), task2.getStatus());
	}

}