package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.services.ProductService;
import dev.sarvesh.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto result = fakeStoreProductServiceClient.getProductById(id);
        return convertFakeStoreProductDtoToGenericDto(result);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        FakeStoreProductDto result =  fakeStoreProductServiceClient.createProduct(product);
        return convertFakeStoreProductDtoToGenericDto(result);

    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        FakeStoreProductDto[] results = fakeStoreProductServiceClient.getAllProducts();
        return Arrays.stream(results).map(FakeStoreProductService::convertFakeStoreProductDtoToGenericDto).toList();
    }

    @Override
    public boolean deleteProductById(Long id) {
        return fakeStoreProductServiceClient.deleteProductById(id);
    }

    @Override
    public boolean updateProductById(Long id, GenericProductDto product) {
        return fakeStoreProductServiceClient.updateProductById(id,product);
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericDto = new GenericProductDto();
        genericDto.setId(fakeStoreProductDto.getId());
        genericDto.setPrice(fakeStoreProductDto.getPrice());
        genericDto.setDescription(fakeStoreProductDto.getDescription());
        genericDto.setImage(fakeStoreProductDto.getImage());
        genericDto.setTitle(fakeStoreProductDto.getTitle());
        genericDto.setCategory(fakeStoreProductDto.getCategory());
        return genericDto;
    }
}
