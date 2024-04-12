package com.Ctavio.todosimple.reposritories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ctavio.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
