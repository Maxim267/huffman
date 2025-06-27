package hashTable;

import listQ.LinkQ;
import listQ.ListQ;

/**
 * Хеш-таблица {@code HashTable}
 * отображает диапазон значений ключа на диапазон значений индекса при помощи хеш-функции,
 * с разрешением коллизий по методу цепочек с использованием связанного списка {@code ListQ}
 */
public class HashTable {
    private final int arraySize;
    /**
     * Структура хеш-таблицы построена на массиве связанных списков
     */
    public ListQ[] array;
    public HashTable(int size) {
        arraySize = size;
        array = new ListQ[arraySize];
    }
    public int getArraySize() {
        return arraySize;
    }
    public ListQ[] getArray() {
        return array;
    }
    /**
     * Хеш-функция {@code hashFunc} преобразует строковый ключ в числовое значение с помощью метода Горнера:
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     */
    public int hashFunc(String key) {
        int hashValue = 0;
        for(int j = 0; j < key.length(); ++j) {
            int letter = key.charAt(j);
            hashValue = (hashValue * j + letter) % arraySize;
        }
        return hashValue;
    }
    /**
     * Помещает в структуру хеш-таблицы строковый ключ с инкрементированным целочисленным значением при многократном вызове этого метода
     */
    public void inc(String key) {
        put(key, 1, true);
    }
    /**
     * Помещает в структуру хеш-таблицы строковый ключ с целочисленным значением
     */
    public void put(String key, int value, boolean isInc) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new ListQ();
        }
        array[hashValue].insertSData(key, value, isInc);
    }
    /**
     * Помещает в структуру хеш-таблицы для строкового ключа его строковое закодированное значение
     */
    public void putCode(String key, String letter) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new ListQ();
        }
        array[hashValue].insertBData(key, letter);
    }
    public int get(String key) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            return -1;
        }
        LinkQ link = array[hashValue].find(key);
        if (link == null) {
            return -1;
        }
        return link.getIData();
    }
    /**
     * Получает из структуры хеш-таблицы для строкового ключа его строковое закодированное значение
     */
    public String getCode(String key) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            return null;
        }
        LinkQ current = array[hashValue].getLinkQRoot();
        while (current != null && !current.getSData().equals(key)) {
            current = current.getNextChild();
        }
        if (current != null) {
            return current.getBCode();
        }
        return null;
    }
    /**
     * Отображает содержимое Хеш-таблицы
     */
    public void displayHash() {
        System.out.println("............................");
        for(int j = 0; j < array.length; ++j) {
            if(array[j] != null) {
                System.out.print(j + ": ");
                array[j].displayList();
            }
        }
    }
}
