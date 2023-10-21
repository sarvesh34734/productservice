package dev.sarvesh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders extends BaseModel{

    @ManyToMany
    // @JoinTable is used if we want to cofigure the mappig many-many table in our own way
    @JoinTable(
            name = "product_orders_mapping", // name of table,
            joinColumns = @JoinColumn(name="order_id"),// name of current class table,
            inverseJoinColumns = @JoinColumn(name="product_id") // name of other table
    )
    private List<Product> product;
}
