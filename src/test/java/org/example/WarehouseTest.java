package org.example;

import org.golfshop.entities.Category;
import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.Product;
import org.golfshop.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class WarehouseTest {

    Warehouse warehouseTest = new Warehouse();

    @Test
    public void IsWarehouseEmpty() {

        CopyOnWriteArrayList<Product> productList = warehouseTest.getProductList();

        assertThat(productList)
                .isEmpty();
    }

    @Test
    public void whenWeCreateAProductTheListShouldNotBeEmpty() {
        warehouseTest.createANewProduct("Test Putter", 9, 3999, Category.PUTTER);
        CopyOnWriteArrayList<Product> productList = warehouseTest.getProductList();

        assertThat(productList)
                .isNotEmpty();
    }

    @Test
    public void giveAnEmptyNameThrowException() {

        assertThrows(IllegalArgumentException.class, () -> {
            warehouseTest.createANewProduct("", 9, 3, Category.IRONS);
        });
    }

    @Test
    public void giveNegativePriceThrowException() {

        assertThrows(IllegalArgumentException.class, () -> {
            warehouseTest.createANewProduct("gffg", 9, -3, Category.IRONS);
        });
    }

    @Test
    public void WhenYouUpdateAnProductTheUpdatedVersionShouldChangeToChangedValue() {
        warehouseTest.createANewProduct("Test Putter", 9, 3999, Category.PUTTER);
        warehouseTest.updateAnExistingProduct(1, "NewName", 8, Category.IRONS);
        CopyOnWriteArrayList<Product> productList = warehouseTest.getProductList();

        Product product = null;
        for (Product p : productList
        ) {
            if (p.getId() == 1) product = p;
        }
        assertThat(product).isNotNull().extracting("id", "name").containsExactly(1, "NewName");
    }


    @Test
    public void WhenUpdateAProductAndGivenAnEmptyNameThrowException() {

        assertThrows(IllegalArgumentException.class, () -> {
            warehouseTest.updateAnExistingProduct(1, "", 3, Category.IRONS);
        });
    }

    @Test
    public void CheckIfYouGetAllProductFromWareHouse() {
        warehouseTest.createANewProduct("Test Putter", 9, 3999, Category.PUTTER);
        warehouseTest.createANewProduct("Test Driver", 9, 3999, Category.DRIVER);

        List<ImmutableObjectProduct> productList = warehouseTest.getAllProduct();
        assertThat(productList).hasSize(2);
    }

    @Test
    public void ShouldReturnAllPutters() {
        warehouseTest.addMockDateToWarehouse();
        List<ImmutableObjectProduct> productList = warehouseTest.getProductByCategorySortAfterName(Category.PUTTER);

        assertThat(productList).extracting("category").containsOnly(Category.PUTTER);
    }

    @Test
    public void returnSameCategorySortedByTheNameFromATOZ() {
        warehouseTest.addMockDateToWarehouse();

        assertThat(warehouseTest.getProductByCategorySortAfterName(Category.PUTTER).get(0).name()).startsWith("Sp");
    }

 /*   @Test
    public void ShouldOnlyReturnProductsCreatedAfterDesiredDate() {
        Warehouse warehouseTest = new Warehouse();
        warehouseTest.addMockDateToWarehouse();
        List<ImmutableObjectProduct> productList = warehouseTest.getProductAfterDesiredDateDescendingOrder(LocalDate.of(2023, 9, 23));

        boolean OnlyProductsAfterDesiredDate = productList.stream().anyMatch(p -> p.createdDate().isAfter(LocalDate.of(2023, 9, 23)));

        assertThat(OnlyProductsAfterDesiredDate).isTrue();
    }*/

    @Test
    public void getProductAfterDesiredDateShouldBeInDescendingOrder() {
        warehouseTest.addMockDateToWarehouse();

        LocalDate desiredDate = LocalDate.of(2023, 9, 23);

        assertThat(warehouseTest.getProductAfterDesiredDateDescendingOrder(desiredDate)).extracting("createdDate").isSortedAccordingTo(Collections.reverseOrder());
    }

    @Test
    public void ShouldOnlyReturnProductThatBeenModified() {
        warehouseTest.addMockDateToWarehouse();

        assertThat(warehouseTest.getProductThatHaveBeenModified().size()).isEqualTo(6);

    }
}
