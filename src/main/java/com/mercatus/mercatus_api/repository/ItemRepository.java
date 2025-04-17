package com.mercatus.mercatus_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercatus.mercatus_api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
