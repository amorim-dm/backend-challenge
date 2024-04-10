package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show() {
			return DefaultResponse.ok(retrieveAllTasksService.execute());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		return DefaultResponse.ok().entity(retrieveTaskByIdService.execute(taskId));
	}

	@POST
	public Response create(TaskView task) {
			return DefaultResponse.ok().entity(createTaskService.execute(TaskDTO.create()
			.setTitle(task.getTitle())
			.setDescription(task.getDescription())));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		Task updatedTask = this.retrieveTaskByIdService.execute(taskId);
		if(updatedTask != null) {
			updatedTask.setTitle(task.getTitle());
			updatedTask.setDescription(task.getDescription());
			return DefaultResponse.ok().entity(this.updateTaskService.execute(updatedTask));
		} else {
			return DefaultResponse.notFound().statusCode(400);
		}
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		this.deleteTaskService.execute(taskId);
		return DefaultResponse.ok().entity("Deleted");
	}

}
