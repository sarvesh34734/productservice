package dev.sarvesh.productservice.thirdpartyclients.productservice;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductById(Long id) throws NotFoundException;

    FakeStoreProductDto createProduct(GenericProductDto product);

    FakeStoreProductDto[] getAllProducts();

    boolean deleteProductById(Long id);

    boolean updateProductById(Long id, GenericProductDto product);
}
