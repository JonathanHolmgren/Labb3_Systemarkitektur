package org.golfshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.golfshop.entities.Category;
import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.Product;
import org.golfshop.mapper.ProductMapper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.golfshop.mockdata.MockData.getProducts;
import static org.golfshop.service.GenerateId.generateNewId;


@ApplicationScoped
public class Warehouse implements IWarehouse {

    private static CopyOnWriteArrayList<Product> productList;

    public Warehouse() {
        productList = new CopyOnWriteArrayList<>();
    }



    public static CopyOnWriteArrayList<Product> getProductList() {
        return productList;
    }

    @Override
    public void showAll() {

        for (Product p : productList) {
            System.out.println("--------------------");
            System.out.println("ID: " + p.getId());
            System.out.println("Name: " + p.getName());
            System.out.println("Price: " + p.getPrice() + " SEK");
            System.out.println("Rating: " + p.getRating());
            System.out.println("Category: " + p.getCategory());
            System.out.println("CreateDate: " + p.getCreatedDate());
            System.out.println("ModifiedDate: " + p.getLastmodified());
            System.out.println("--------------------");

        }

    }
    @Override
    public void addMockDateToWarehouse() {
        this.productList = getProducts();
    }

    @Override
    public void createANewProduct(String name, double rating, double price, Category category) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("You have tried to create a product without a name");
        } else if (price < 0) {
            throw new IllegalArgumentException("You have tried to create a product with a negative price");
        }
        this.productList.add(new Product(generateNewId(), name, rating, price, category));
    }
    @Override
    public void updateAnExistingProduct(int id, String name, double rating, Category category) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("You have tried to update a product without a name");
        }
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setName(name);
                p.setRating(rating);
                p.setCategory(category);
                p.setLastmodified(LocalDate.now());
            }
        }

    }
    @Override
    public List<ImmutableObjectProduct> getAllProduct() {
        return productList.stream()
                .map(ProductMapper::ConvertToImmutableRecord)
                .toList();
    }
    @Override
    public Optional<ImmutableObjectProduct> getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .map(ProductMapper::ConvertToImmutableRecord).findFirst();
    }
    @Override
    public List<ImmutableObjectProduct> getProductByCategorySortAfterName(Category category) {
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .sorted(Comparator.comparing(Product::getName))
                .map(ProductMapper::ConvertToImmutableRecord)
                .toList();
    }
    @Override
    public List<ImmutableObjectProduct> getProductAfterDesiredDateDescendingOrder(LocalDate date) {
        return productList.stream()
                .filter(product -> product.getCreatedDate().isAfter(date))
                .sorted(Comparator.comparing(Product::getCreatedDate).reversed())
                .map(ProductMapper::ConvertToImmutableRecord)
                .toList();
    }
    @Override
    public List<ImmutableObjectProduct> getProductThatHaveBeenModified() {
        return productList.stream()
                .filter(product -> !product.getCreatedDate().isEqual(product.getLastmodified()))
                .map(ProductMapper::ConvertToImmutableRecord)
                .toList();
    }
}


