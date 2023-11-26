package org.golfshop.mapper;

import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.Product;

import java.time.format.DateTimeFormatter;

public class ProductMapper {

    public static ImmutableObjectProduct ConvertToImmutableRecord(Product product) {
        return new ImmutableObjectProduct(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getRating(),
                product.getCategory(),
                product.getCreatedDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                product.getLastmodified().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }


}
