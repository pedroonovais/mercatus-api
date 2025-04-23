package com.mercatus.mercatus_api.specfication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.mercatus.mercatus_api.model.Item;
import com.mercatus.mercatus_api.model.ItemFilter;
import com.mercatus.mercatus_api.model.ItemType;

public abstract class ItemSpecification {

    public static Specification<Item> withFilters(ItemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + filter.name().toLowerCase() + "%"
                ));
            }

            if (filter.type() != null && !filter.type().isBlank()) {
                List<ItemType> matchingTypes = Arrays.stream(ItemType.values())
                    .filter(e -> e.name().toLowerCase().contains(filter.type().toLowerCase()))
                    .toList();

                if (!matchingTypes.isEmpty()) {
                    predicates.add(root.get("type").in(matchingTypes));
                }
                else {
                    predicates.add(cb.isNull(root.get("type")));
                }
            }

            if (filter.rarity() != null && !filter.rarity().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("rarity")),
                    "%" + filter.rarity().toLowerCase() + "%"
                ));
            }   

            if (filter.minPrice() != null && filter.maxPrice() != null) {
                predicates.add(cb.between(root.get("price"), filter.minPrice(), filter.maxPrice()));
            }

            if (filter.minPrice() != null && filter.maxPrice() == null) {
                predicates.add(cb.equal(root.get("price"), filter.minPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
