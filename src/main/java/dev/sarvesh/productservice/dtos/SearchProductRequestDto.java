package dev.sarvesh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductRequestDto {

    private String query;
    private Integer pageSize;
    private Integer pageNumber;
    private List<SortParameter> sortParameters;

}
