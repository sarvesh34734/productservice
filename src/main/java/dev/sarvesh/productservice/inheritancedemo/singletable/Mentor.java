package dev.sarvesh.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_mentor") // st is single table
@DiscriminatorValue(value = "3")
public class Mentor extends User {
    private double averageRating;
}
