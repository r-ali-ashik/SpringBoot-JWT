package com.aliashik.authapi.controller;

import com.aliashik.authapi.entity.Task;
import com.aliashik.authapi.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		return new ResponseEntity(taskRepository.save(task), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List> getTasks() {
		return new ResponseEntity(taskRepository.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> editTask(@PathVariable long id, @RequestBody Task task) {
		Task existingTask = taskRepository.findById(id).get();
		Assert.notNull(existingTask, "Task not found");
		existingTask.setDescription(task.getDescription());
		return new ResponseEntity(taskRepository.save(existingTask), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteTask(@PathVariable long id) {
		taskRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
