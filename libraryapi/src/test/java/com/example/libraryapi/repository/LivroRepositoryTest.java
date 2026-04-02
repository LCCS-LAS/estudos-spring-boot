package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        livro.setTitulo("Aprendendo");
        livro.setDataPublicacao(LocalDate.of(2005,5,20));

        Autor autor = autorRepository.findById(UUID.fromString("3189a915-0b39-4738-aaf5-6015ece701e9")).orElse(null);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("46465646546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Aprendendo Spring Boot");
        livro.setDataPublicacao(LocalDate.of(2005,5,20));

        Autor autor = new Autor();
        autor.setNome("Bruna");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990,8,9));

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarTestManual(){
        Livro livro = new Livro();
        livro.setIsbn("46465646546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Spring Boot");
        livro.setDataPublicacao(LocalDate.of(2005,5,20));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2005,8,9));
        autorRepository.save(autor);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void autualizarAutor(){
        var livroParaAutalizar = livroRepository.findById(UUID.fromString("bca55b14-3d53-45fc-9ee7-38fb1f92dd00")).orElse(null);

        Autor autor = autorRepository.findById(UUID.fromString("33c85e35-ac61-44e7-b0fa-433dad68306f")).orElse(null);

        if (livroParaAutalizar != null) {
            livroParaAutalizar.setAutor(autor);
        }

        if (livroParaAutalizar != null) {
            livroRepository.save(livroParaAutalizar);
        }
    }

    @Test
    void delete(){
        UUID id = (UUID.fromString("833e17d3-c64e-451e-b97e-b46eec0f07a3"));
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = (UUID.fromString("44820e89-abff-49fa-948b-57aa3172206b"));
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.print(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void listarLivrosTitulo(){
        List<Livro> livrosTitulo = livroRepository.findByTitulo("Spring Boot");
        livrosTitulo.forEach(System.out::println);
    }

    @Test
    void listarLivrosIsbn(){
        List<Livro> livrosIsbn = livroRepository.findByIsbn("46465646546");
        livrosIsbn.forEach(System.out::println);
    }

    @Test
    void listarLivroTituloPreco(){
        var preco = BigDecimal.valueOf(100.00);
        List<Livro> livrosTituloPreco = livroRepository.findByTituloAndPreco("Spring Boot", preco);
        livrosTituloPreco.forEach(System.out::println);
    }

    @Test
    void listarTodos(){
        List<Livro> listarTodos = livroRepository.listarTodos();
        listarTodos.forEach(System.out::println);
    }

    @Test
    void listarGenero(){
        List<Livro> listGenero = livroRepository.findByGenero(GeneroLivro.MISTERIO);
        listGenero.forEach(System.out::println);
    }
}