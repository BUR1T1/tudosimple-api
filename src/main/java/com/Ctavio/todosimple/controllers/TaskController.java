package com.Ctavio.todosimple.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Ctavio.todosimple.models.Task;
import com.Ctavio.todosimple.models.User;
import com.Ctavio.todosimple.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Task")
@Validated
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @GetMapping ("/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id){
        Task obj = this.taskService.findById(id);
        return ResponseEntity.ok(obj);

    }
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId){

        List<Task> objs = taskService.findAllByUserId(userId);
        
        return ResponseEntity.ok().body(objs);
    }

    public ResponseEntity<Void> create(@Validated @RequestBody Task obj){
        this.taskService.create(obj);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/(id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        }

        public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
            obj.setId(id);
            this.taskService.update(obj);

            return ResponseEntity.noContent().build();

        }

        public ResponseEntity<Void> delete(@PathVariable long id) {

            this.taskService.delete(id);

            return ResponseEntity.noContent().build();
        }

     
}
