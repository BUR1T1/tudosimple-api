package com.Ctavio.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "Task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "description",length = 266 , nullable =false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 256)
    private String description;

    public Task(){
        
    }
    public Task(long id, User user, @NotNull @NotEmpty @Size(min = 1, max = 256) String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }
    //seguindo os passos do video 
    //porem foi modificado com ajufda do chatGPT para que posssa ser implemntado com atualizações 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        // Verifica se obj é uma instância de Task
        if (!(obj instanceof Task))
            return false;
    
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }
    
}
