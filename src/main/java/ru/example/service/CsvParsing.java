package ru.example.service;

import java.util.List;
import java.util.Map;

public interface CsvParsing {
    String fileName = "C:\\Users\\danis\\Desktop\\airports-search-\\airports.csv";
    Map<String, List<Long>> readAllFile(Integer columNumber);

    String[] readLine(Long n);
}
