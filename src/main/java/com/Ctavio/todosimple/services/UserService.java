package com.Ctavio.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ctavio.todosimple.models.User;
import com.Ctavio.todosimple.reposritories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    
    public User findById(Long Id){
        Optional<User> user = this.userRepository.findById(Id);

        return user.orElseThrow(() -> new RuntimeException( "Usuário não encontrado! Id: " + Id + ", Tipo: " + User.class.getName()));

    }

    @Transactional
    public User Create(User obj) {
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }
     
    @Transactional
    public User Update(User obj){

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);

    }
    
    public User delete(long Id){
        findById(Id);
        try {
            this.userRepository.deleteById(Id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
        return null;
         

    }


}