package dev.sarvesh.productservice.services;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto getProductById(String id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto createProduct(GenericProductDto genericProduct, String token,String email) throws AccessDeniedException;

    List<GenericProductDto> getAllProducts();

    void deleteProductById(String id);

    boolean updateProductById(Long id, GenericProductDto product);

    Page<GenericProductDto> searchProducts(String query,Pageable pageable);
}
