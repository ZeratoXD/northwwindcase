package com.northwind.csv.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.northwind.csv.service.DataExtractionService;
import com.northwind.csv.service.DataWritingService;

@RestController
@RequestMapping("/pipeline")
public class PipelineController {

    @Autowired
    private DataExtractionService dataExtractionService;

    @Autowired
    private DataWritingService dataWritingService;

    @Autowired
    private DataBaseWritingService databaseWritingService;

    // Endpoint para executar a extração e escrita no disco local
    @GetMapping("/run-extraction")
    public String runExtraction() throws IOException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Extrair dados do PostgreSQL
        var orders = dataExtractionService.extractOrdersFromDatabase();
        String ordersFilePath = "data/" + date + "/orders.json";
        dataWritingService.writeDataToFile(ordersFilePath, orders);

        // Extrair dados do CSV
        String csvFilePath = "path/to/order_details.csv";
        var orderDetails = dataExtractionService.extractOrderDetailsFromCSV(csvFilePath);
        String orderDetailsFilePath = "data/" + date + "/order_details.json";
        dataWritingService.writeDataToFile(orderDetailsFilePath, orderDetails);

        return "Extração e escrita no disco local concluídas com sucesso!";
    }

    // Endpoint para executar a carga no PostgreSQL
    @GetMapping("/run-load")
    public String runLoad() throws IOException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String ordersFilePath = "data/" + date + "/orders.json";
        databaseWritingService.writeOrdersToDatabase(ordersFilePath);

        return "Carga no PostgreSQL concluída com sucesso!";
    }

    // Endpoint para verificar o status do pipeline
    @GetMapping("/status")
    public String getStatus() {
        return "Pipeline está operacional.";
    }
}