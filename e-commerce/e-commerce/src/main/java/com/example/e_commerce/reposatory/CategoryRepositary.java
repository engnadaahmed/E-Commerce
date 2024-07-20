package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepositary extends JpaRepository<Category, Long> {
    public Category findByName(String name);

    @Query("SELECT c from Category c where c.name=:name And c.parentCategory.name=:ParentCategoryName")
   public Category findByNameAndParent(@Param("name")String name, @Param("ParentCategoryName")String ParentCategoryName);
}
