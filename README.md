Для реализации основной идеи были использованы индексы, аналогичные тем, что используются в базах данных. Идея заключается в следующем:

    Сначала мы читаем весь файл и сохраняем его содержимое в TreeMap, который выбран из-за того, что операция получения значения (get) в TreeMap работает за время O(log n). Ключом является значение определенного столбца строки, а значением - позиция строки в файле, где это значение встречается.
    Далее с помощью бинарного поиска (O(log n)) мы ищем строки, которые начинаются на введенный префикс.
    Наконец, мы читаем строки, которые нашли в предыдущем шаге.
К сожалению, пришлось использовать RandomAccessFile, который работает не так быстро, как хотелось бы (но я нашел только данный вариант). Код работает без использования дополнительных библиотек. Вот и все :)