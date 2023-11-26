package org.golfshop.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

import static org.golfshop.service.GenerateId.generateNewId;

public record NewProduct (
        int id,

        @NotEmpty(message = "Empty names not allowed")
        String name,

        @Min(value = 0, message = "Rating should not be lower than 0")
        @Max(value = 10, message = "Rating should not be higher than 10")
        double rating,

        @Min(value = 0, message = "Price should not be less than 0")
        double price,

        @Pattern(regexp = "^(IRONS|DRIVER|PUTTER)$", message = "Category should be IRONS,DRIVER or PUTTER.")
        String category,

        String createdDate,
        String lastmodified



)
{
    public NewProduct(String name, double rating, double price, String category, String createdDate, String lastmodified) {
        this(0, name, rating, price, category, createdDate, lastmodified);
    }

    public NewProduct {
        id = generateNewId();
        createdDate = String.valueOf(LocalDate.now());
        lastmodified = String.valueOf(LocalDate.now());
    }

    @Override
        public String toString() {
                return "NewProduct{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", rating=" + rating +
                        ", price=" + price +
                        ", category='" + category + '\'' +
                        ", createdDate='" + createdDate + '\'' +
                        ", lastmodified='" + lastmodified + '\'' +
                        '}';
        }
}
