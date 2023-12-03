package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.models.Category;
import dev.sarvesh.productservice.models.Price;
import dev.sarvesh.productservice.models.Product;
import dev.sarvesh.productservice.repositories.ProductRepository;
import dev.sarvesh.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class DefaultProductService implements ProductService {

    private ProductRepository productRepository;

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        Optional<Product> product =  productRepository.findById(UUID.fromString(id));
        if(product.isEmpty()){
            throw new NotFoundException(String.format("Product with id %s not found",id));
        }
        return productToGenericProduct(product.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProduct) {
        assert genericProduct != null;
        Product productModel = genericProductToProduct(genericProduct);
        Product savedProduct =  productRepository.save(productModel);
        return productToGenericProduct(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DefaultProductService::productToGenericProduct).collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(String id)  {

        productRepository.deleteById(UUID.fromString(id));

    }

    @Override
    public boolean updateProductById(Long id, GenericProductDto product) {
        Product savedProduct = productRepository.getReferenceById(UUID.fromString(Long.toString(id)));

        return false;
    }

    private static GenericProductDto productToGenericProduct(Product product){
        GenericProductDto genericProduct = new GenericProductDto();
        genericProduct.setId(product.getUuid());
        genericProduct.setImage(product.getImage());
        genericProduct.setTitle(product.getTitle());
        genericProduct.setPrice(product.getPrice().getPrice());
        genericProduct.setCategory(product.getCategory().getName());
        genericProduct.setDescription(product.getDescription());
        return genericProduct;
    }

    private static Product genericProductToProduct(GenericProductDto genericProductDto){
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setImage(genericProductDto.getImage());
        product.setPrice(new Price("Rs",genericProductDto.getPrice()));
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        product.setCategory(category);
        product.setDescription(genericProductDto.getDescription());
        return product;
    }
}
