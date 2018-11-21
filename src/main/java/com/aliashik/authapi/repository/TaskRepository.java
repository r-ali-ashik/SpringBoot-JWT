package com.aliashik.authapi.repository;

import com.aliashik.authapi.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
