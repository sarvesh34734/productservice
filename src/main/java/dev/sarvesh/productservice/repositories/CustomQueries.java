package dev.sarvesh.productservice.repositories;

public interface CustomQueries {
     String FIND_ALL_BY_TITLE = "select * from product where title = :title limit 1";
//     String FIND_ALL_BY_TITLE_HQL = "select Product from "
}
