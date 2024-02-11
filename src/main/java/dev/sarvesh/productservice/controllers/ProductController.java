package dev.sarvesh.productservice.controllers;

import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.services.ProductService;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.AccessDeniedException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products/")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        List<GenericProductDto> products = productService.getAllProducts();
        assert products != null;
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") String id) throws NotFoundException {
        GenericProductDto product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") String id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<GenericProductDto> createProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                           @RequestParam String email,
                                                           @RequestBody GenericProductDto product) throws AccessDeniedException {
        GenericProductDto response = productService.createProduct(product,token,email);
        return ResponseEntity.ok().body(response);
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

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleAccessDeniedException(Exception ex){

    }

}
