package dev.sarvesh.productservice;

import dev.sarvesh.productservice.models.Category;
import dev.sarvesh.productservice.models.Price;
import dev.sarvesh.productservice.models.Product;
import dev.sarvesh.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductserviceApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    public ProductserviceApplication(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Price p = new Price("Rupee",20);
        Product p1 = new Product();
        p1.setPrice(p);
        Category c = new Category();
        c.setName("Phone");
        p1.setTitle("I Phone");
        p1.setDescription("The best phone ever");
        p1.setCategory(c);
        productRepository.save(p1);
        System.out.println(productRepository.findAllByPrice_Currency("rupee"));
        System.out.println("count of products where price = 20 : "+productRepository.countAllByPrice_Price(20.0));
        System.out.println(productRepository.findDistinctByTitle("I Phone").size());
        System.out.println(productRepository.findByPrice_PriceOrderByPrice_price(20));
        System.out.println(productRepository.findByCurrencyAndTitle("Rupee","I Phone"));
    }
}
