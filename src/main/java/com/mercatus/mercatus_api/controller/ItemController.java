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
import com.mercatus.mercatus_api.repository.ItemRepository;
import com.mercatus.mercatus_api.specfication.ItemSpecification;
import com.mercatus.mercatus_api.model.Item;
import com.mercatus.mercatus_api.model.ItemFilter;
import com.mercatus.mercatus_api.model.Character;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {
    
    @Autowired
    private ItemRepository repository;

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    @Operation(
        summary = "Listar todas os itens", 
        description = "Retorna todas os itens cadastrados",
        tags = {"Item"}
    )
    public Page<Item> index(ItemFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        return repository.findAll(ItemSpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Item create(@RequestBody @Valid Item item) {
        var owner = item.getOwner();

        if (owner != null) {
            item.setOwner(getCharacter(owner.getId()));
        }

        log.info("Cadastrando item " + item.getName());
        return repository.save(item);
    }

    @GetMapping("{id}")
    public Item get(@PathVariable Long id) {
        log.info("Buscando item " + id);
        return getItem(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando item " + id);
        repository.delete(getItem(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        log.info("Atualizando item " + id + " " + item);	

        getItem(id);
        item.setId(id);
        return repository.save(item);
    }

    private Item getItem(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + id + " não encontrado")
                );
    }

    private Character getCharacter(Long id) {
        return characterRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem " + id + " não encontrado")
                );
    }
}
