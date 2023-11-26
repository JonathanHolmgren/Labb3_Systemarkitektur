package org.golfshop.entities;

public record ImmutableObjectProduct(
        int id,
        String name,
        double rating,
        double price,
        Category category,
        String createdDate,
        String lastmodified
) {

}
