package com.mercatus.mercatus_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.mercatus.mercatus_api.model.Character;
import com.mercatus.mercatus_api.model.CharacterType;
import com.mercatus.mercatus_api.model.Item;
import com.mercatus.mercatus_api.model.ItemType;
import com.mercatus.mercatus_api.repository.CharacterRepository;
import com.mercatus.mercatus_api.repository.ItemRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {

        var characters = List.of(
            Character.builder().name("Pedro").characterType(CharacterType.GUERREIRO).level(88).coins(2300).build(),
            Character.builder().name("Rodrigo").characterType(CharacterType.MAGO).level(89).coins(1200).build(),
            Character.builder().name("Laura").characterType(CharacterType.ARQUEIRO).level(75).coins(1800).build(),
            Character.builder().name("Thiago").characterType(CharacterType.GUERREIRO).level(92).coins(1500).build(),
            Character.builder().name("Beatriz").characterType(CharacterType.GUERREIRO).level(81).coins(2100).build(),
            Character.builder().name("Marcos").characterType(CharacterType.MAGO).level(77).coins(1600).build(),
            Character.builder().name("Fernanda").characterType(CharacterType.ARQUEIRO).level(85).coins(1900).build(),
            Character.builder().name("Ricardo").characterType(CharacterType.GUERREIRO).level(90).coins(2000).build(),
            Character.builder().name("Juliana").characterType(CharacterType.ARQUEIRO).level(86).coins(1700).build(),
            Character.builder().name("Lucas").characterType(CharacterType.MAGO).level(79).coins(1750).build(),
            Character.builder().name("Patrícia").characterType(CharacterType.ARQUEIRO).level(83).coins(1650).build(),
            Character.builder().name("André").characterType(CharacterType.GUERREIRO).level(91).coins(1850).build(),
            Character.builder().name("Isabela").characterType(CharacterType.MAGO).level(80).coins(1550).build(),
            Character.builder().name("Felipe").characterType(CharacterType.MAGO).level(78).coins(1400).build(),
            Character.builder().name("Camila").characterType(CharacterType.ARQUEIRO).level(82).coins(1950).build()
        );

        characterRepository.saveAll(characters);

        var items = List.of(
            Item.builder().name("Espada de Ferro").type(ItemType.ARMA).rarity("Comum").price(500).owner(characters.get(0)).build(),
            Item.builder().name("Escudo de Carvalho").type(ItemType.ARMADURA).rarity("Incomum").price(700).owner(characters.get(1)).build(),
            Item.builder().name("Poção de Cura").type(ItemType.POÇÃO).rarity("Comum").price(150).owner(characters.get(2)).build(),
            Item.builder().name("Anel da Sorte").type(ItemType.ACESSÓRIO).rarity("Raro").price(1200).owner(characters.get(3)).build(),
            Item.builder().name("Machado de Guerra").type(ItemType.ARMA).rarity("Raro").price(1300).owner(characters.get(4)).build(),
            Item.builder().name("Armadura de Couro").type(ItemType.ARMADURA).rarity("Comum").price(600).owner(characters.get(5)).build(),
            Item.builder().name("Poção de Mana").type(ItemType.POÇÃO).rarity("Incomum").price(200).owner(characters.get(6)).build(),
            Item.builder().name("Colar da Resistência").type(ItemType.ACESSÓRIO).rarity("Raro").price(1100).owner(characters.get(7)).build(),
            Item.builder().name("Espada Flamejante").type(ItemType.ARMA).rarity("Épico").price(2500).owner(characters.get(8)).build(),
            Item.builder().name("Cota de Malha").type(ItemType.ARMADURA).rarity("Incomum").price(900).owner(characters.get(9)).build(),
            Item.builder().name("Poção de Velocidade").type(ItemType.POÇÃO).rarity("Raro").price(300).owner(characters.get(10)).build(),
            Item.builder().name("Bracelete da Força").type(ItemType.ACESSÓRIO).rarity("Épico").price(2000).owner(characters.get(11)).build(),
            Item.builder().name("Arco Longo").type(ItemType.ARMA).rarity("Comum").price(550).owner(characters.get(12)).build(),
            Item.builder().name("Elmo de Aço").type(ItemType.ARMADURA).rarity("Raro").price(1000).owner(characters.get(13)).build(),
            Item.builder().name("Poção de Invisibilidade").type(ItemType.POÇÃO).rarity("Épico").price(500).owner(characters.get(14)).build(),
            Item.builder().name("Amuleto de Proteção").type(ItemType.ACESSÓRIO).rarity("Lendário").price(3000).owner(characters.get(0)).build(),
            Item.builder().name("Lança de Gelo").type(ItemType.ARMA).rarity("Raro").price(1800).owner(characters.get(1)).build(),
            Item.builder().name("Armadura do Dragão").type(ItemType.ARMADURA).rarity("Lendário").price(4000).owner(characters.get(2)).build(),
            Item.builder().name("Poção de Fúria").type(ItemType.POÇÃO).rarity("Raro").price(350).owner(characters.get(3)).build(),
            Item.builder().name("Tiara da Sabedoria").type(ItemType.ACESSÓRIO).rarity("Épico").price(2200).owner(characters.get(4)).build()
        );

        itemRepository.saveAll(items);
    }
}
