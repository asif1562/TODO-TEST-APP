package com.todo.service;

import com.todo.model.Task;
import com.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    public List<Task> getAllTasks() {
    return todoRepository.findAll();
    }

    public void createTask(String title) {
        Task task=new Task();
        task.setCompleted(false);
        task.setTitle(title);
        todoRepository.save(task);
    }

    public void deleteTask(Long id) {
        todoRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task=todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid task id"));
        task.setCompleted(!task.isCompleted());
        todoRepository.save(task);
    }
}
