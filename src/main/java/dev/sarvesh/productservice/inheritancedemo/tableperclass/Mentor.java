package dev.sarvesh.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_mentor") // tpc is table per class
public class Mentor extends User {
    private double averageRating;
}
