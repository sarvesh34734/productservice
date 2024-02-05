package dev.sarvesh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "category")

    // this mappedBy is used to tell spring that this is the same relation
    // as that in product represented by category attribute otherwise spring will treat it differently.
    private List<Product> products;
}
