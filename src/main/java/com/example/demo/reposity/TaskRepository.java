package com.example.demo.reposity;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>  {

    Optional<Task> findById(Long id);
    List<Task> findAll();
    boolean deleteTaskBy(Long id);




}
