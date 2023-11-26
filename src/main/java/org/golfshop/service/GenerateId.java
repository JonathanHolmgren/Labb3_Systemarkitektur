package org.golfshop.service;

import org.golfshop.entities.Product;

import java.util.concurrent.CopyOnWriteArrayList;

public class GenerateId {


    public static int generateNewId() {


        CopyOnWriteArrayList<Product> productsList = Warehouse.getProductList();

        int id = productsList.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0);

        if (id < 1) id = 1;
        else id = id + 1;

        return id;
    }


}
