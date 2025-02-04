package com.northwind.csv.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DataWritingService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeDataToFile(String filePath, List<?> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}