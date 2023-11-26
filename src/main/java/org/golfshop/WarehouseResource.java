package org.golfshop;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.golfshop.entities.Category;
import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.NewProduct;
import org.golfshop.exceptions.EmptyListException;
import org.golfshop.exceptions.InvalidIdException;
import org.golfshop.interceptor.LogCreateProduct;
import org.golfshop.interceptor.Logging;
import org.golfshop.service.IWarehouse;

import java.util.List;
import java.util.Optional;


@Path("")
@Logging
public class WarehouseResource {

    private IWarehouse iWarehouse;
    // private static final Logger logger = LoggerFactory.getLogger(WarehouseResource.class);

    public WarehouseResource() {
    }

    @Inject
    public WarehouseResource(IWarehouse iWarehouse) {
        this.iWarehouse = iWarehouse;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/dba/addmock")
    public String addMockProducts() {
        iWarehouse.addMockDateToWarehouse();
        return "The mock products is added.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/products")
    public List<ImmutableObjectProduct> getAllProducts() {
        return iWarehouse.getAllProduct();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/products/{id}")
    public ImmutableObjectProduct getProductById(@PathParam("id") int id) {
        Optional<ImmutableObjectProduct> product = iWarehouse.getProductById(id);
        if (product.isEmpty()) {
            throw new InvalidIdException("Invalid id");
        }
        return product.get();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/products/category/{cg}")
    public List<ImmutableObjectProduct> getProductsByCategory(@PathParam("cg") String cg) {

            Category category = Category.valueOf(cg.toUpperCase());
            List<ImmutableObjectProduct> productList = iWarehouse.getProductByCategorySortAfterName(category);
            if (productList.isEmpty()) {
                throw new EmptyListException("No products in this category");
            }
            return productList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/products")
    @LogCreateProduct
    public void createANewProduct(@Valid NewProduct product) {

        String name = product.name();
        double rating = product.rating();
        double price = product.price();
        Category category = Category.valueOf(product.category());

        iWarehouse.createANewProduct(name, rating, price, category);
    }

}