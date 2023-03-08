package ru.example.service.impl;

import ru.example.service.Search;

import java.util.*;

public class BinarySearchImpl implements Search {

    @Override
    public List<String> prefixSearch(String[] array, String prefix) {
        List<String> result = new ArrayList<>();
        prefix = prefix.toLowerCase();
        int i = Arrays.binarySearch(array, prefix);
        if (i < 0) {
            i = -(i + 1);
        }
        while (i < array.length && array[i].startsWith(prefix)) {
            result.add(array[i]);
            i++;
        }

        return result;
    }
}
