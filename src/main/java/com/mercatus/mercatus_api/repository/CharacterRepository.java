package com.mercatus.mercatus_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mercatus.mercatus_api.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    
}
