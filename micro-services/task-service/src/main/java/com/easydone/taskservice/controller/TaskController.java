package com.easydone.taskservice.controller;

import com.easydone.taskservice.VO.ResponseTemplate;
import com.easydone.taskservice.model.Task;
import com.easydone.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/")
@CrossOrigin(origins = "*")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createNewTask(@RequestBody Task task){
        return taskService.createNewTask(task);
    }

    @GetMapping(path = "{taskId}")
    public ResponseTemplate getTaskByTaskId(@PathVariable("taskId") Long taskId){
        return taskService.getTaskByTaskId(taskId);
    }
}
