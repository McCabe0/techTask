package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskManagerController.class)
@Import(SecurityConfig.class)
public class TaskManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private Task taskOne;
    private Task taskTwo;

    @BeforeEach
    public void setup() {
        taskOne = new Task(1L, "TASK ONE", "the task", TaskStatus.IN_PROGRESS.name());
        taskTwo = new Task(2L, "TASK TWO", "the task", TaskStatus.COMPLETED.name());
    }

    @Test
    public void testGetAllTasks() throws Exception {
        given(taskService.getAllTasks()).willReturn(Arrays.asList(taskOne, taskTwo));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(taskOne.getId()))
                .andExpect(jsonPath("$[1].id").value(taskTwo.getId()));
    }

    @Test
    public void testGetTaskById() throws Exception {
        given(taskService.getTask(taskOne.getId())).willReturn(Optional.of(taskOne));

        mockMvc.perform(get("/tasks/{id}", taskOne.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskOne.getId()));
    }

    @Test
    public void testGetTaskById_NotFound() throws Exception {
        given(taskService.getTask(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(get("/tasks/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTask() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskOne)))
                .andExpect(status().isOk());

        verify(taskService).createTask(any());
    }

    @Test
    public void testUpdateTask() throws Exception {
        mockMvc.perform(put("/tasks/{id}", taskOne.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskOne)))
                .andExpect(status().isOk());

        verify(taskService).updateTask(eq(taskOne.getId()), any());
    }


    @Test
    public void testDeleteTask() throws Exception {
        given(taskService.deleteTask(taskOne.getId())).willReturn(true);

        mockMvc.perform(delete("/tasks/{id}", taskOne.getId()))
                .andExpect(status().isNoContent());
    }

}

