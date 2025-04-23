package com.mercatus.mercatus_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mercatus.mercatus_api.repository.CharacterRepository;
import com.mercatus.mercatus_api.specfication.CharacterSpecification;
import com.mercatus.mercatus_api.model.Character;
import com.mercatus.mercatus_api.model.CharacterFilter;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/characters")
@Slf4j
public class CharacterController {
    
    @Autowired
    private CharacterRepository repository;

    @GetMapping
    @Operation(
        summary = "Listar todas os personagens", 
        description = "Retorna todas os personagens cadastradas",
        tags = {"Character"}
    )
    public Page<Character> index(CharacterFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        return repository.findAll(CharacterSpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Character create(@RequestBody @Valid Character character) {
        log.info("Cadastrando personagem " + character.getName());
        return repository.save(character);
    }

    @GetMapping("{id}")
    public Character get(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return getCharacter(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando personagem " + id);
        repository.delete(getCharacter(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public Character update(@PathVariable Long id, @RequestBody Character character) {
        log.info("Atualizando personagem " + id + " " + character);	

        getCharacter(id);
        character.setId(id);
        return repository.save(character);
    }

    private Character getCharacter(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem " + id + " não encontrado")
                );
    }
}
