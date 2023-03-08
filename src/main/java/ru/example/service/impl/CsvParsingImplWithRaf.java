package ru.example.service.impl;

import ru.example.service.CsvParsing;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class CsvParsingImplWithRaf implements CsvParsing {



    @Override
    public Map<String, List<Long>> readAllFile(Integer columNumber) {
        File file = new File(fileName);
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            Map<String, List<Long>> index = new TreeMap<>();
            String line;
            long pos = 0;

            while ((line = raf.readLine()) != null) {


                String[] fields = line.split(",");
                String key = fields[columNumber - 1].toLowerCase().replace("\"", "");
                if (!index.containsKey(key)) {
                    index.put(key, new ArrayList<>());
                }
                index.get(key).add(pos);
                pos = raf.getFilePointer();
            }
            return index;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String[] readLine(Long n) {
        String[] result;
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            raf.seek(n);
            result = raf.readLine().split(",");
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}