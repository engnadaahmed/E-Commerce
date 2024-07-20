package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.OrdeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrdeItem, Long> {
}
