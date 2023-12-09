package com.devsuperior.dscatalog.Repositories;

import com.devsuperior.dscatalog.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
