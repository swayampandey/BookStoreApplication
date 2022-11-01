package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData,Integer> {
    @Query(value = "select * from orders where user_id = :userId", nativeQuery = true)
    List<OrderData> findAllByUserId(int userId);
}
