package com.mercatus.mercatus_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Character {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obrigatorio")
    private String name;

    @NotNull(message = "classe obrigatorio")
    private CharacterType characterType;

    @Max(value = 99, message = "level nao pode ser maior que 99")
    @Min(value = 1, message = "level nao pode ser menor que 1")
    private Integer level;

    @Positive(message = "coins nao podem ser negativas")
    private Integer coins;
}
