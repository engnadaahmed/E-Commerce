package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
   // List<Order> findByUserId(Long userId);
@Query("SELECT o FROM Order o WHERE o.user.id= :userId AND (o.orderStatus='PLACED' OR o.orderStatus='CONFIRMED' OR o.orderStatus='SHIPPING' OR o.orderStatus='DELIVERED' )")
    public List<Order> getUsersOrder(@Param("userId") Long userId);
}
