package com.example.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValitador {

    private TodoRepository todoRepository;

    public TodoValitador(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validar(TodoEntity todo){
        if (existeTodoDescricao(todo.getDescricao())){
            throw new IllegalArgumentException("Ja existe todo com essa descrição");
        }
    }

    private boolean existeTodoDescricao(String descricao){
        return todoRepository.existsByDescricao(descricao);
    }
}
