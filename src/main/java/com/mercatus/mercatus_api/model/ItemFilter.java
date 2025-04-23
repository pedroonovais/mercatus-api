package com.mercatus.mercatus_api.model;

public record ItemFilter(String name, String type, Integer minPrice, Integer maxPrice, String rarity) {
}