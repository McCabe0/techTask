package com.example.demo.service;

import com.example.demo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();

    Optional<Task> getTask(final Long id);

    void createTask(final Task task);

    void updateTask(final Long id, Task task);

    boolean deleteTask(final Long id);

}
