package backend.challenge.modules.task.services;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(KikahaRunner.class)
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		TaskDTO taskDTO = TaskDTO.create().setTitle("TEST").setDescription("TEST DESC");
		final Task task = createTaskService.execute(taskDTO);
		Assert.assertEquals(taskDTO.getTitle(), task.getTitle());
		Assert.assertEquals(taskDTO.getDescription(), task.getDescription());
	}

}