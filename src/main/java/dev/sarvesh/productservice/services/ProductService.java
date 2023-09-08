package dev.sarvesh.productservice.services;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    FakeStoreProductDto getProductById(Long id) throws NotFoundException;

    ResponseEntity<FakeStoreProductDto> createProduct(GenericProductDto product);

    ResponseEntity<FakeStoreProductDto[]> getAllProducts();

    boolean deleteProductById(Long id);

    boolean updateProductById(Long id, GenericProductDto product);
}
