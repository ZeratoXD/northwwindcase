package com.northwind.csv.service;




import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northwind.csv.model.Order;
import com.northwind.csv.repository.OrderRepository;

@Service
public class DatabaseWritingService {

    @Autowired
    private OrderRepository orderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeOrdersToDatabase(String filePath) throws IOException {
        List<Order> orders = objectMapper.readValue(new File(filePath), new TypeReference<List<Order>>() {});
        orderRepository.saveAll(orders);
    }
}