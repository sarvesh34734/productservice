package dev.sarvesh.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_ta")
@PrimaryKeyJoinColumn(name = "user_id") // it will have FK by name user_id joined on id of user
public class TA extends User {
    private Double averageRating;
}
