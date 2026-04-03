package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.AutorDTO;
import com.example.libraryapi.model.Autor;
import com.example.libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;
    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorDTO>salvar(@RequestBody AutorDTO autor){
        Autor autorEntidade = autor.mapearParaAutor();
        autorService.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO>obterDetalhes(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autor = autorService.obterDadosAutor(idAutor);

        if (autor.isPresent()){
            Autor autor1 = autor.get();
            AutorDTO dto = new AutorDTO(autor1.getId(), autor1.getNome(), autor1.getDataNascimento(), autor1.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}
