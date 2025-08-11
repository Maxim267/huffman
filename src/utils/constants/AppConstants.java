package utils.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Константы приложения.
 */
public final class AppConstants {
    /**
     * Создает константы приложения.
     */
    private AppConstants() {}

    /**
     * Многобайтовый символ новой строки в формате WINDOWS (используется при декодировании).
     */
    public static final String WINDOWS_NEW_ROW = "\r\n";

    /**
     * Однобайтовый символ новой строки в формате UNIX (используется при кодировании).
     */
    public static final String UNIX_NEW_ROW = "\n";

    /**
     * Символ замены символа новой строки, чтобы исключить переход на новую строку (используется при выводе в выходной поток).
     */
    public static final String PRINT_NEW_ROW = "LF";
    /**
     * Символ разделитель ключа и данных при выводе в файл.
     */
    public static final String FILE_KEY_DELIMITER_VALUE = " : ";

    /**
     * Двоичное значение максимального количество пробелов.
     * Используется для позиционирования вывода вершины дерева.
     * Позиционирование каждого следующего уровня последовательно уменьшает это значение вдвое.
     */
    public static final int MAX_BLANKS = 65536;

    /**
     * Начальный размер кучи.
     */
    public static final int HEAP_INIT_CAPACITY = 11;

    /**
     * Размер хеш-таблицы (на основе тестов).
     */
    public static final int HASH_SIZE = 101; // 277

    /**
     * Стандартная кодировка символов.
     */
    public static final Charset STD_CHARSET = StandardCharsets.UTF_8;
}
