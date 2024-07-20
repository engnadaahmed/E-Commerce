package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_Categ  extends JpaRepository<Category, Long> {
}
