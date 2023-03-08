package ru.example.app;

import org.jetbrains.annotations.NotNull;
import ru.example.service.CsvParsing;
import ru.example.service.Search;
import ru.example.service.impl.BinarySearchImpl;
import ru.example.service.impl.CsvParsingImplWithRaf;

import java.util.*;

public class Main {
    public static void main(String @NotNull [] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            CsvParsing parsing = new CsvParsingImplWithRaf();
            Search search = new BinarySearchImpl();
            int columnNumber = Integer.parseInt(args[0]);

            Map<String, List<Long>> index = parsing.readAllFile(columnNumber);
            String[] array = index.keySet().toArray(new String[0]);

            while (true) {
                System.out.println("Введите префикс");

                String prefix = scanner.nextLine();
                if (prefix.equals("!quit")) {
                    break;
                }
                long start = System.currentTimeMillis();

                List<String> stringWithPrefix = search.prefixSearch(array, prefix);

                for (String str : stringWithPrefix) {
                    for (Long pos : index.get(str)) {
                        String[] temp = parsing.readLine(pos);
                        System.out.println(str + " " + Arrays.toString(temp));
                    }
                }

                long end = System.currentTimeMillis();

                System.out.println("size: " + stringWithPrefix.size() + "time: " + (end - start) + "ms");
            }

            System.out.println("Введите текст для поиска или введите \"!quit\" для выхода: ");

        }
        System.out.println("Выход из программы.");
    }
}
