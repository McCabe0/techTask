package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskManagerController {

    private final TaskService itemService;

    public TaskManagerController(final TaskService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(itemService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getITaskById(@PathVariable final Long id) {
        Optional<Task> item = itemService.getTask(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Void> createTask(@RequestBody final Task task) {
        itemService.createTask(task);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable final Long id, @RequestBody final Task item) {
        itemService.updateTask(id, item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable final Long id) {
        boolean deleted = itemService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
