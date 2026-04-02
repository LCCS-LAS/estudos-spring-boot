package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003,8,9));
        var autorSalvoTest = autorRepository.save(autor);
        System.out.println("Autor Salto: " + autorSalvoTest);
    }

    @Test
    public void atualizarAutorTest(){
        var id = UUID.fromString("3189a915-0b39-4738-aaf5-6015ece701e9");
        Optional<Autor>possivelAutor = autorRepository.findById(id);
        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            autorEncontrado.setDataNascimento(LocalDate.of(1999,8,9));
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> autorList = autorRepository.findAll();
        autorList.forEach(System.out::println);
    }

    @Test
    public void countTets(){
        System.out.println("Quantidade de Autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("82c85b3d-873a-49d8-835b-93ddb1e8b568");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("8f6e9aa1-0ff6-4a06-9a1f-6a303a612207");
        var autorTest = autorRepository.findById(id).get();
        autorRepository.delete(autorTest);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Silva");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1995,8,9));

        Livro livro = new Livro();
        livro.setIsbn("999999999");
        livro.setPreco(BigDecimal.valueOf(90));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("JPA");
        livro.setDataPublicacao(LocalDate.of(1990,5,20));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("88888888");
        livro2.setPreco(BigDecimal.valueOf(90));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("JPA Hibernate");
        livro2.setDataPublicacao(LocalDate.of(1990,5,20));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listaLivrosAutor(){
        var id = UUID.fromString("7ef72984-c984-461e-8e7b-a651ad381554");
        var autor = autorRepository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::print);
    }
}
