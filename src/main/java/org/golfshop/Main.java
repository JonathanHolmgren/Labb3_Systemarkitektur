package org.golfshop;

import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.service.Warehouse;

import java.util.Optional;


public class  Main {


    public static void main(String[] args) {

        Warehouse example = new Warehouse();
        example.addMockDateToWarehouse();


        // example.showAll();


        // getAllProduct()
       // List<ImmutableObjectProduct> exp = example.getAllProduct();
       // exp.forEach(System.out::println);


        // getProductById()
        Optional<ImmutableObjectProduct> exp1 = example.getProductById(8);
        System.out.println(exp1);

        //     getProductByCategorySortAfterName()
       // List<ImmutableObjectProduct> exp2 = example.getProductByCategorySortAfterName(Category.valueOf("Putter".toUpperCase()));
        //    exp2.forEach(System.out::println);

        //    getProductAfterDesiredDateDescendingOrder()
       // List<ImmutableObjectProduct> exp3 = example.getProductAfterDesiredDateDescendingOrder(LocalDate.of(2023, 9, 23));
          //  exp3.forEach(System.out::println);

        //     getProductThatHaveBeenModified()
      //  List<ImmutableObjectProduct> exp4 = example.getProductThatHaveBeenModified();
         //   exp4.forEach(System.out::println);


    }


}
