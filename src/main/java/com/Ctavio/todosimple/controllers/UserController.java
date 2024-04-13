package com.Ctavio.todosimple.controllers;

import java.net.URI;
import java.security.Provider.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Ctavio.todosimple.models.User;
import com.Ctavio.todosimple.models.User.CreatUser;
import com.Ctavio.todosimple.models.User.UpdateUser;
import com.Ctavio.todosimple.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/")
public class UserController {
    
    private UserService userService;

    @GetMapping("/(id)")
    //uma entidade de resposposta do tipo usuario,para o front-end
    public ResponseEntity<User> findById(@PathVariable Long id){

        User obj = this.userService.findById(id);

        return ResponseEntity.ok().body(obj);
   /*Este controlador Spring MVC é responsável por manipular requisições relacionadas aos usuários.
    Ele define um endpoint GET para buscar um usuário pelo ID.
     Aqui está uma explicação das partes principais do código: */


    }
    @PostMapping
    @Validated(CreatUser.class)
    
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.Create(obj);
           URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/(id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
/*O método create no controlador UserController é responsável por lidar com requisições POST para criar novos usuários no sistema.
Ao receber uma requisição com os dados do usuário a ser criado, o método valida esses dados e então chama o serviço UserService para realizar a criação do usuário.
Após a criação bem-sucedida, o método retorna uma resposta HTTP 201 Created, indicando que o usuário foi criado com sucesso.
Além disso, no cabeçalho da resposta é incluída a URI do novo recurso criado, permitindo que o cliente saiba onde encontrar o usuário recém-criado.
Este método contribui para a funcionalidade de cadastro de usuários em um sistema,
seguindo as práticas RESTful e garantindo a integridade dos dados através das validações adequadas. */

@PutMapping("/(id)")
@Validated(UpdateUser.class)
public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
    obj.setId(id);
    this.userService.Update(obj);
    return ResponseEntity.noContent().build();
}
@DeleteMapping("/(id)")
public ResponseEntity<Void> delete(@PathVariable long id){

    this.userService.delete(id);
    return ResponseEntity.noContent().build();
}
}
