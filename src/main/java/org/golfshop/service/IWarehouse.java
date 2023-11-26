package org.golfshop.service;

import org.golfshop.entities.Category;
import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


public interface IWarehouse {


    public static CopyOnWriteArrayList<Product> getProductList() {
        return null;
    }

    public void showAll();

    public void addMockDateToWarehouse();

    public void createANewProduct(String name, double rating, double price, Category category);

    public void updateAnExistingProduct(int id, String name, double rating, Category category);

    public List<ImmutableObjectProduct> getAllProduct();

    public Optional<ImmutableObjectProduct> getProductById(int id);

    public List<ImmutableObjectProduct> getProductByCategorySortAfterName(Category category);

    public List<ImmutableObjectProduct> getProductAfterDesiredDateDescendingOrder(LocalDate date);

    public List<ImmutableObjectProduct> getProductThatHaveBeenModified();


}
