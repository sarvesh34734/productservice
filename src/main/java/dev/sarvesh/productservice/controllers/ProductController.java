package dev.sarvesh.productservice.controllers;

import dev.sarvesh.productservice.dtos.FakeStoreProductDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        FakeStoreProductDto[] products = productService.getAllProducts().getBody();
        assert products != null;
        List<GenericProductDto> response = Arrays.stream(products)
                .map(ProductController::toGenericProductDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        FakeStoreProductDto product = productService.getProductById(id);
        GenericProductDto response = toGenericProductDto(product);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){
        boolean response = productService.deleteProductById(id);
        if(response){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto product){
        FakeStoreProductDto response = productService.createProduct(product).getBody();

        return ResponseEntity.ok().body(toGenericProductDto(response));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product) throws URISyntaxException {
        boolean isUpdated = productService.updateProductById(id,product);
        if(isUpdated){
            URI uri = new URI("https://fakestoreapi.com/products/"+id);
            return ResponseEntity.created(uri).build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    private static GenericProductDto toGenericProductDto(FakeStoreProductDto product){
        GenericProductDto response = new GenericProductDto();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setCategory(product.getCategory());
        response.setImage(product.getImage());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        return response;
    }

}
