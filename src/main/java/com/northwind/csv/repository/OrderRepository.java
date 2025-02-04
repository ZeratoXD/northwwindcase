package com.northwind.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.northwind.csv.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}