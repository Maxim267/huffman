package hashTable;

/**
 * Функциональный интерфейс хеш-функции.
 * @param <T> тип значения.
 */
@FunctionalInterface
public interface HashFunc<T> {
    /**
     * Получает хеш-ключ обработкой заданного значения.
     * @param value заданное значение.
     * @return хеш-ключ.
     */
    int hashFunc(T value);
}
