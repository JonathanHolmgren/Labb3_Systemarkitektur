package org.golfshop.mockdata;

import org.golfshop.entities.Category;
import org.golfshop.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MockData {

    private static CopyOnWriteArrayList<Product> makeMockProducts() {

        CopyOnWriteArrayList<Product> productList = new CopyOnWriteArrayList<>();

        productList.add(new Product(1, "T200 - Steel", 9, 9999, Category.IRONS, LocalDate.of(2022, 4, 12)));
        productList.add(new Product(2, "Mavrik 22 - Steel", 7, 5499, Category.IRONS, LocalDate.of(2022, 6, 30)));
        productList.add(new Product(3, "JPX 921 HM 5-Pw - Steel", 8, 6999, Category.IRONS, LocalDate.of(2023, 2, 16)));
        productList.add(new Product(4, "M4 21 - steel", 3, 4699, Category.IRONS, LocalDate.of(2023, 5, 8)));
        productList.add(new Product(5, "Mavrik 22", 5, 2999, Category.DRIVER, LocalDate.of(2023, 3, 4)));
        productList.add(new Product(6, "SIM2 MAX Ventus BLue", 9, 3999, Category.DRIVER));
        productList.add(new Product(7, "Stealth 2 HD", 7, 5299, Category.DRIVER));
        productList.add(new Product(8, "Super Select Newport 2", 9, 4999, Category.PUTTER, LocalDate.of(2023, 9, 27)));
        productList.add(new Product(9, "Spider Tour Black SS", 8, 2499, Category.PUTTER));

        return productList;
    }

    public static CopyOnWriteArrayList<Product> getProducts() {
        return makeMockProducts();
    }


}
