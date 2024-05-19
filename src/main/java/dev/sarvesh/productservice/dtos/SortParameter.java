package dev.sarvesh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParameter {

    private String sortBy;

    private SortType sortType;

}
