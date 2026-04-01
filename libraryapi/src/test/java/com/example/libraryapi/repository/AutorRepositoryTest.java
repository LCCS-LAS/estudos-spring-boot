package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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


}
