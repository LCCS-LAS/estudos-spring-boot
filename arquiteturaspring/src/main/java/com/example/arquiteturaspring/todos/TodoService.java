package com.example.arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoValitador todoValitador;
    private EmailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValitador todoValitador, EmailSender mailSender) {
        this.todoRepository = todoRepository;
        this.todoValitador = todoValitador;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity salvarTodo){
        todoValitador.validar(salvarTodo);
        return todoRepository.save(salvarTodo);
    }

    public void atualizarStatus(TodoEntity todo){
        todoRepository.save(todo);
        String status = todo.getConcluido() == Boolean.TRUE ? "Concluido" : "Não Concluido";
        mailSender.enviar("Todo de codigo" + todo.getId() + " foi atualizado para " + status );
    }

    public TodoEntity buscarPorId(Integer id){
        return todoRepository.findById(id).orElse(null);
    }
}
