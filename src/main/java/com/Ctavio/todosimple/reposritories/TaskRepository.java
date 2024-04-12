package com.Ctavio.todosimple.reposritories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ctavio.todosimple.models.Task;

public interface TaskRepository extends JpaRepository <Task , Long>{

   // List<Task> findByUser_Id(Long id);

   /*No geral, este método de consulta é 
   utilizado para recuperar uma lista de tarefas
    associadas a um determinado usuário,
    onde o ID do usuário é fornecido como parâmetro.
   @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
   List<Task> findByUser_Id(@Param ("Id") Long id);*/


   /*No geral, este método de consulta é utilizado para recuperar uma 
   lista de tarefas associadas a um determinado usuário,
    onde o ID do usuário é fornecido como parâmetro, 
   utilizando uma consulta SQL nativa. */
   @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
   List<Task> findByUser_Id(@Param("id") Long id);

}