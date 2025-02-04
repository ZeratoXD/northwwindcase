package com.northwind.csv.service;



import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northwind.csv.model.Order;
import com.northwind.csv.repository.OrderRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class DataExtractionService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> extractOrdersFromDatabase() {
        return orderRepository.findAll();
    }

    public List<String[]> extractOrderDetailsFromCSV(String csvFilePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            return reader.readAll();
        }
    }
}
