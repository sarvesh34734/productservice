package dev.sarvesh.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    // Product : Category
    //     1   :    1
    //     m   :    1
    // final mapping - m : 1 - manyToOne
    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="category") // just renames it to category instead of category_id
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Price price;

    private Integer inventory_count;

}
