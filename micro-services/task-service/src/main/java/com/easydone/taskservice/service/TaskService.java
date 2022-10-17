package com.easydone.taskservice.service;

import VO.ResponseTemplate;
import VO.User;
import com.easydone.taskservice.model.Task;
import com.easydone.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TaskService(TaskRepository taskRepository, RestTemplate restTemplate) {
        this.taskRepository = taskRepository;
        this.restTemplate = restTemplate;
    }

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public ResponseTemplate getTaskByTaskId(Long taskId) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        Task task = taskRepository.findByTaskId(taskId);
        User user = restTemplate.getForObject("http://localhost:4001/api/v1/users/"+task.getUserId(), User.class);
        responseTemplate.setTask(task);
        responseTemplate.setUser(user);
        return responseTemplate;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
