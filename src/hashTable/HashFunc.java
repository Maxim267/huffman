package hashTable;

/**
 * Функциональный интерфейс хеш-функции.
 * @param <T> тип значения
 */
@FunctionalInterface
public interface HashFunc<T> {
    /**
     * Получить хеш-ключ обработкой заданного значения Хеш-функцией.
     * @param value заданное значение.
     * @return хеш-ключ.
     */
    int hashFunc(T value);
}
