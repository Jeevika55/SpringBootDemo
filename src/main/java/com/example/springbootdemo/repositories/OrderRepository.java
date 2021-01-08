package com.example.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdemo.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
