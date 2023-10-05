package dev.sarvesh.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass // used for making it base class for category and product
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name="uuidgenerator",strategy = "uuid2")
    @Column(name = "id",columnDefinition = "binary(16)",nullable = false,updatable = false)
    private UUID uuid;
}
