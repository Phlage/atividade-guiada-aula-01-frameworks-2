package com.example.task_api.controller;

import com.example.task_api.modle.Task;
import com.example.task_api.repository.TaskRepository;
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
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return taskRepository.findById(id);
    }

    // Faltava o @PostMapping no código da atividade
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        // Ignora o id enviado no JSON: quem gera o id de uma tarefa nova é o servidor.
        // Sem isso, enviar "id": 1 e depois criar outra tarefa sem id gera dois ids iguais.
        task.setId(0);
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task updateTask) {
        updateTask.setId(id);
        return taskRepository.save(updateTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.delete(id);
    }
}
