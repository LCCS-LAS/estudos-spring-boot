package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvar(){
        Livro livro = new Livro();
        livro.setIsbn("46465646546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Aprendendo Spring Boot");
        livro.setDataPublicacao(LocalDate.of(2005,5,20));

        Autor autor = autorRepository.findById(UUID.fromString("3189a915-0b39-4738-aaf5-6015ece701e9")).orElse(null);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

}