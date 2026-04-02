package com.example.libraryapi.service;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.AutorRepository;
import com.example.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionalService {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Transactional
    public void executar(){
        //salva autor
        Autor autor = new Autor();
        autor.setNome("Leandro");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1998,8,9));

        autorRepository.save(autor);

        //salva livro
        Livro livro = new Livro();
        livro.setIsbn("123456789");
        livro.setPreco(BigDecimal.valueOf(90));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Transactional");
        livro.setDataPublicacao(LocalDate.of(1997,5,20));
        livro.setAutor(autor);
    }
}
