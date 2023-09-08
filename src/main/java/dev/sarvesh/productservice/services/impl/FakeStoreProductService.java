package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(FakeStoreProductService.class);

    private static final String BASE_URL = "https://fakestoreapi.com/products";
    private static final String PRODUCT_BY_ID_REQUEST_URL = BASE_URL + "/{id}";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        we can pass dynamic variable like id as it has curly braces in requestUrl {id}
        FakeStoreProductDto product =  restTemplate
                .getForEntity(PRODUCT_BY_ID_REQUEST_URL, FakeStoreProductDto.class,id).getBody();

        if(product == null){
            throw new NotFoundException("Product with id : "+ id + " Not found");
        }

        return product;
    }

    @Override
    public ResponseEntity<FakeStoreProductDto> createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate
                .postForEntity(BASE_URL,product,FakeStoreProductDto.class);
    }

    @Override
    public ResponseEntity<FakeStoreProductDto[]> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity(BASE_URL,FakeStoreProductDto[].class);
    }

    @Override
    public boolean deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try{
            restTemplate.delete(PRODUCT_BY_ID_REQUEST_URL,id);
            return true;
        }
        catch(Exception ex){
            LOG.error("exception occurred : ",ex);
            return false;
        }
    }

    @Override
    public boolean updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try{
            restTemplate.put(PRODUCT_BY_ID_REQUEST_URL,product,id);
            return true;
        }
        catch(Exception ex){
            LOG.error("exception occured",ex);
            return false;
        }
    }
}
