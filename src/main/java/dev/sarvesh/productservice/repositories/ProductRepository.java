package dev.sarvesh.productservice.repositories;

import dev.sarvesh.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByPrice_PriceOrderByPrice_price(double price); // This means find product where in price object price is = price

    List<Product> findAllByPrice_Currency(String currency);

    int countAllByPrice_Price(double price);

    List<Product> findDistinctByTitle(String title);

    // custom query
    @Query(value="select p from Product p where p.price.currency =:currency and p.title = :title") // nativeQuery means it is a sql query. Variables are passed by :title
    // if we write join in above query, the result will be from both table
    // but as per method response it will try to map it to Product
    // nativeQuery = false default means it is Hibernate query
    List<Product> findByCurrencyAndTitle(String currency,String title);
}
