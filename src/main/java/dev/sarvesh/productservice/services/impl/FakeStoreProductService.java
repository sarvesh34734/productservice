package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.services.ProductService;
import dev.sarvesh.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {

        GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForHash().get("PRODUCTS",id);

        if(null !=  genericProductDto){
            System.out.println("serving from redis cache");
            return genericProductDto;
        }

        FakeStoreProductDto result = fakeStoreProductServiceClient.getProductById(Integer.parseInt(id));
        GenericProductDto fetchedProduct = convertFakeStoreProductDtoToGenericDto(result);
        redisTemplate.opsForHash().put("PRODUCTS",id,fetchedProduct);
        return fetchedProduct;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        FakeStoreProductDto result =  fakeStoreProductServiceClient.createProduct(product);
        return convertFakeStoreProductDtoToGenericDto(result);

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProduct, String token,String email) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        // get a product from api call and cache it to redis

        FakeStoreProductDto[] results = fakeStoreProductServiceClient.getAllProducts();
        return Arrays.stream(results).map(FakeStoreProductService::convertFakeStoreProductDtoToGenericDto).toList();
    }

    @Override
    public void deleteProductById(String id) {
        fakeStoreProductServiceClient.deleteProductById(Long.getLong(id));
    }

    @Override
    public boolean updateProductById(Long id, GenericProductDto product) {
        return fakeStoreProductServiceClient.updateProductById(id,product);
    }

    @Override
    public Page<GenericProductDto> searchProducts(String query, Pageable pageable) {
        return null;
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericDto = new GenericProductDto();
        genericDto.setId(UUID.randomUUID());
        genericDto.setPrice(fakeStoreProductDto.getPrice());
        genericDto.setDescription(fakeStoreProductDto.getDescription());
        genericDto.setImage(fakeStoreProductDto.getImage());
        genericDto.setTitle(fakeStoreProductDto.getTitle());
        genericDto.setCategory(fakeStoreProductDto.getCategory());
        return genericDto;
    }
}
