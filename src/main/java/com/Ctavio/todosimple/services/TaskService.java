package com.Ctavio.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ctavio.todosimple.models.Task;
import com.Ctavio.todosimple.models.User;
import com.Ctavio.todosimple.reposritories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(long id){

        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada!!! id:" + id + Task.class.getName()));
    }
@Transactional
    public Task create(Task obj){

        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(0);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        
        return obj;

    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){
        findById(0);

        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas.");
        }

    }
}

