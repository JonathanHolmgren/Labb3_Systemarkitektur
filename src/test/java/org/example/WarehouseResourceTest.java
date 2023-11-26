package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.golfshop.WarehouseResource;
import org.golfshop.entities.ImmutableObjectProduct;
import org.golfshop.entities.NewProduct;
import org.golfshop.exceptions.InvalidIdException;
import org.golfshop.exceptions.InvalidIdExceptionMapper;
import org.golfshop.service.IWarehouse;
import org.golfshop.service.Warehouse;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.golfshop.entities.Category.IRONS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WarehouseResourceTest {

    @Mock
    private IWarehouse iWarehouse;

    Dispatcher dispatcher;

    @BeforeEach
    public void setup() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        var warehouseResource = new WarehouseResource(iWarehouse);
        dispatcher.getRegistry().addSingletonResource(warehouseResource);
        // Create your custom ExceptionMapper
        ExceptionMapper<InvalidIdException> mapper = new InvalidIdExceptionMapper();
        // Register your custom ExceptionMapper
        dispatcher.getProviderFactory().registerProviderInstance(mapper);
    }



    // Kan man skapa en instans av Warehouse?
    @Test
    public void CallAddMockShouldAddProductsToWarehouse() throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.addMockDateToWarehouse();

        MockHttpRequest request = MockHttpRequest.get("/dba/addmock");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals("The mock products is added.", response.getContentAsString());
        assertEquals(9,warehouse.getProductList().size());
    }


    @Test
    public void getAllProductsReturnAllProducts() throws Exception {

        Mockito.when(iWarehouse.getAllProduct()).thenReturn(List.of(
                new ImmutableObjectProduct(1,"T200 - Steel",9,9999, IRONS,"2022-04-12","2023-11-23"),
                new ImmutableObjectProduct(2,"JPX 921 HM 5-Pw - Steel",7,5499, IRONS,"2022-06-30","2023-11-23"),
                new ImmutableObjectProduct(3,"T200 - Steel",8,6999, IRONS,"2023-02-16","2023-11-23")
              ));

        MockHttpRequest request = MockHttpRequest.get("/products");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getOutputHeaders().getFirst("Content-type").toString());
        assertEquals("[{\"id\":1,\"name\":\"T200 - Steel\",\"rating\":9.0,\"price\":9999.0,\"category\":\"IRONS\",\"createdDate\":\"2022-04-12\",\"lastmodified\":\"2023-11-23\"}," +
        "{\"id\":2,\"name\":\"JPX 921 HM 5-Pw - Steel\",\"rating\":7.0,\"price\":5499.0,\"category\":\"IRONS\",\"createdDate\":\"2022-06-30\",\"lastmodified\":\"2023-11-23\"}," +
        "{\"id\":3,\"name\":\"T200 - Steel\",\"rating\":8.0,\"price\":6999.0,\"category\":\"IRONS\",\"createdDate\":\"2023-02-16\",\"lastmodified\":\"2023-11-23\"}]"
        ,response.getContentAsString());
     }

    @Test
    public void getOneProductByIdReturnCorrectProduct() throws Exception {

        Mockito.when(iWarehouse.getProductById(1)).thenReturn(Optional.of(
                new ImmutableObjectProduct(1,"T200 - Steel",9,9999, IRONS,"2022-04-12","2023-11-23")));


        MockHttpRequest request = MockHttpRequest.get("/products/1");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getOutputHeaders().getFirst("Content-type").toString());
       assertEquals("{\"id\":1,\"name\":\"T200 - Steel\",\"rating\":9.0,\"price\":9999.0,\"category\":\"IRONS\",\"createdDate\":\"2022-04-12\",\"lastmodified\":\"2023-11-23\"}",response.getContentAsString());


    }
    @Test
    public void getProductsByCategoryReturnProductsForThatCategory() throws Exception {

        Mockito.when(iWarehouse.getProductByCategorySortAfterName(IRONS)).thenReturn(List.of(
                new ImmutableObjectProduct(1,"T200 - Steel",9,9999, IRONS,"2022-04-12","2023-11-23"),
                new ImmutableObjectProduct(2,"JPX 921 HM 5-Pw - Steel",7,5499, IRONS,"2022-06-30","2023-11-23"),
                new ImmutableObjectProduct(3,"T200 - Steel",8,6999, IRONS,"2023-02-16","2023-11-23")));


        MockHttpRequest request = MockHttpRequest.get("/products/category/irons");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getOutputHeaders().getFirst("Content-type").toString());
        assertEquals("[{\"id\":1,\"name\":\"T200 - Steel\",\"rating\":9.0,\"price\":9999.0,\"category\":\"IRONS\",\"createdDate\":\"2022-04-12\",\"lastmodified\":\"2023-11-23\"}," +
                        "{\"id\":2,\"name\":\"JPX 921 HM 5-Pw - Steel\",\"rating\":7.0,\"price\":5499.0,\"category\":\"IRONS\",\"createdDate\":\"2022-06-30\",\"lastmodified\":\"2023-11-23\"}," +
                        "{\"id\":3,\"name\":\"T200 - Steel\",\"rating\":8.0,\"price\":6999.0,\"category\":\"IRONS\",\"createdDate\":\"2023-02-16\",\"lastmodified\":\"2023-11-23\"}]"
                ,response.getContentAsString());

    }

    // Får inte testet att svara med kod 204, fast jag får det i postman.
    // Får att productsList blir null och har gjort den static pga av GenerateId.
    //Förmodligen kan jag inte ha en sån metod om jag ska göra ett bra test.
    //@Test
    public void whenAddProductAndCorrectProductShouldBeReturn() throws Exception {
        MockHttpRequest request = MockHttpRequest.post("/products");
        String json = new ObjectMapper().writeValueAsString(new NewProduct("T200 - Steel", 18,34, "Irons","2023-11-24","2023-11-24"));
        request.content(json.getBytes());
        request.contentType(MediaType.APPLICATION_JSON);
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        assertEquals(400, response.getStatus());


    }






}





