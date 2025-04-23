package com.mercatus.mercatus_api.specfication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.mercatus.mercatus_api.model.CharacterFilter;
import com.mercatus.mercatus_api.model.CharacterType;
import com.mercatus.mercatus_api.model.Character;

public abstract class CharacterSpecification {

    public static Specification<Character> withFilters(CharacterFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + filter.name().toLowerCase() + "%"
                ));
            }

            if (filter.characterType() != null && !filter.characterType().isBlank()) {
                List<CharacterType> matchingTypes = Arrays.stream(CharacterType.values())
                    .filter(e -> e.name().toLowerCase().contains(filter.characterType().toLowerCase()))
                    .toList();

                if (!matchingTypes.isEmpty()) {
                    predicates.add(root.get("characterType").in(matchingTypes));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
