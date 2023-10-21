package dev.sarvesh.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ms_mentor") // ms is mapped super class
public class Mentor extends User{
    private double averageRating;
}
