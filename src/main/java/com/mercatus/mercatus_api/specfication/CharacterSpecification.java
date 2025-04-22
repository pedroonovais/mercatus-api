package com.mercatus.mercatus_api.specfication;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.mercatus.mercatus_api.model.CharacterFilter;
import com.mercatus.mercatus_api.model.Character;

public abstract class CharacterSpecification {

    public static Specification<Character> withFilters(CharacterFilter filter){
        return (root, query, cb) -> {
            
            List<Predicate> predicates = new ArrayList<>();

            if(filter.name() != null){
                predicates.add( cb.like(
                    cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"
                ));
            }

            if(filter.characterType() != null){
                predicates.add( cb.like(
                    cb.lower(root.get("characterType")), "%" + filter.characterType().toLowerCase() + "%"
                ));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
