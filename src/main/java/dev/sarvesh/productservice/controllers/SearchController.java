package dev.sarvesh.productservice.controllers;

import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.dtos.SearchProductRequestDto;
import dev.sarvesh.productservice.dtos.SortParameter;
import dev.sarvesh.productservice.dtos.SortType;
import dev.sarvesh.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
public class SearchController {

    private ProductService productService;

    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchProductRequestDto searchRequest){
        List<SortParameter> sortParameters = searchRequest.getSortParameters();
        Sort sort = sortParameters.isEmpty() ? null : Sort.by(sortParameters.get(0).getSortBy());
        if(sortParameters.get(0).getSortType().equals(SortType.DESC)){
            sort.descending();
        }

        for(int i=1;i<sortParameters.size();i++){
            sort.and(Sort.by(sortParameters.get(i).getSortBy()));

            if(sortParameters.get(i).getSortType().equals(SortType.DESC)){
                sort.descending();
            }
        }

        return productService.searchProducts(searchRequest.getQuery(), PageRequest
                .of(searchRequest.getPageNumber(), searchRequest.getPageSize(),sort));
    }

}
