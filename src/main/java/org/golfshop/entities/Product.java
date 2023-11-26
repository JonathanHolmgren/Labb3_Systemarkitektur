package org.golfshop.entities;


import java.time.LocalDate;

public class Product {

    private int id;


    private String name;


    private double rating;


    private double price;

    private Category category;
    private final LocalDate createdDate;
    private LocalDate lastmodified;


    public Product(int id, String name, double rating, double price, Category category) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.createdDate = LocalDate.now();
        this.lastmodified = LocalDate.now();
    }

    public Product(int id, String name, double rating, double price, Category category, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
        this.lastmodified = LocalDate.now();
    }

    public Product(int id, String name, double rating, double price, Category category, LocalDate createdDate, LocalDate lastmodified) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
        this.lastmodified = lastmodified;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(LocalDate lastmodified) {
        this.lastmodified = lastmodified;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", category=" + category +
                ", createdDate=" + createdDate +
                ", lastmodified=" + lastmodified +
                '}';
    }
}
