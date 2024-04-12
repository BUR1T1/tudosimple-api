package com.Ctavio.todosimple.services;

import java.util.Optional;

import com.Ctavio.todosimple.models.User;

public interface UserRepository {

    Optional<User> findById(Long id);

    User save(User obj);

    void deleteById(long id);

}
