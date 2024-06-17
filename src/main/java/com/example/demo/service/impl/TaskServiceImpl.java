package com.example.demo.service.impl;

import com.example.demo.model.Task;
import com.example.demo.reposity.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTask(final Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void createTask(final Task task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(final Long id, final Task task) {
        taskRepository.save(task);
    }

    @Override
    public boolean deleteTask(final Long id) {
        return taskRepository.deleteTaskBy(id);
    }
}
