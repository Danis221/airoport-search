package ru.example.service.impl;

import ru.example.service.CsvParsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CsvParsingWithBr implements CsvParsing {
    @Override
    public Map<String, List<Long>> readAllFile(Integer columNumber) {
        Map<String, List<Long>> result = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length <  columNumber) {
                    continue;
                }
                String key = values[columNumber - 1];
                key = key.toLowerCase().replace("\"", "");
                if (!result.containsKey(key)) {
                    result.put(key, new ArrayList<>());
                }
                result.get(key).add(Long.parseLong(values[0]));
            }
            reader.close();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] readLine(Long n) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            long currentLineNumber = 1;
            while ((line = reader.readLine()) != null && currentLineNumber < n) {
                currentLineNumber++;
            }
            return line != null ? line.split(",") : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
