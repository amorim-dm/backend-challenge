package backend.challenge.modules.task.services;


import kikaha.core.test.KikahaRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private ICreateTaskService createTaskService;
	private IUpdateTaskProgressService updateTaskProgressService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		this.createTaskService = new CreateTaskService(taskRepository);
		this.updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		Task task = createTaskService.execute(TaskDTO.create().setTitle("TEST").setDescription("TEST DESC"));
		Assert.assertEquals(task.getProgress(), 0);
		updateTaskProgressService.execute(TaskProgressDTO.create().setId(task.getId()).setProgress(120));
		Assert.assertEquals(task.getProgress(), 100);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		Task task = createTaskService.execute(TaskDTO.create().setTitle("TEST").setDescription("TEST DESC"));
		Assert.assertEquals(task.getStatus(), TaskStatus.PROGRESS);
		updateTaskProgressService.execute(TaskProgressDTO.create().setId(task.getId()).setProgress(100));
		Assert.assertEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

}
