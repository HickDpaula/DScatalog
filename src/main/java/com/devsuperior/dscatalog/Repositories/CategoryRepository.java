package com.devsuperior.dscatalog.Repositories;

import com.devsuperior.dscatalog.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
