# Labb2_Systemarkitektur

## Endpoints för Warehouse (http://localhost:8080/laboration2-1.0-SNAPSHOT/api)


### /dba/addmock
GET – Hämtar mockdata


### /products
GET – Retunerar alla produkter
POST – Lägger till en produkt.


    {
    "name": "T200 - Steel",
    
    "rating": 5,
    
    "price": "765",
    
    "category": "IRONS"
    }



### /products/{id}
GET – Retunerar en produkt


### /products/category/{cg}
GET – Retunerar produkter inom kategorin.
Irons
Driver
Putter.

