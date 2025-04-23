package com.mercatus.mercatus_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obrigatorio")
    private String name;

    @NotNull(message = "tipo do item obrigatorio")
    private ItemType type;

    @NotBlank(message = "raridade do item obrigatoria")
    private String rarity;

    @Positive(message = "preco nao pode ser negativo")
    private Integer price;

    @ManyToOne
    private Character owner;

}
