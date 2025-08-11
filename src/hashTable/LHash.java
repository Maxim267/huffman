package hashTable;

import linkedList.LList;
import linkedList.LNode;
import utils.calculations.MathUtils;
import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.util.Arrays;

/**
 * Хеш-таблица (Hash Table)
 * с разрешением коллизий по методу цепочек (Chaining) с использованием связанного списка.
 * Уникальность элементов связанного списка по ключу {@code K} поддерживается.
 * Отображает диапазон значений ключа на диапазон значений индекса с помощью хеш-функции.
 * Элементы в связанном списке упорядочены в соответствии с естественным порядком их ключей {@code K}.
 * @param <K> тип ключей, поддерживаемых связанным списком.
 * @param <V> тип соответствующих ключам данных.
 */
public class LHash<K extends Comparable<K>, V> implements Output, HashFunc<K> {
    /**
     * Массив связанных списков.
     */
//    private LList<K, V>[] array;
    private final LList<K, V>[] array;

    /**
     * Размер хеш-таблицы.
     */
    private int size;

    /**
     * Интерфейс вывода в поток Хеш-таблицы.
     */
    public final Display out = new Display(this::display, "<<< Hash Table: ", ">>>");

    /**
     * Создает пустую хеш-таблицу с заданным размером.
     * Если величина размера не является простым числом, то она автоматически увеличится до ближайшего простого числа.
     * @param size размер хеш-таблицы.
     */
    public LHash(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер хеш-таблицы должен быть больше 0.");
        }
        this.size = MathUtils.getPrime(size);
        this.array = new LList[this.size];
    }

    /**
     * Создает новую хеш-таблицу на основе заданного массива связанных списков.
     * @param array массив списков.
     */
    public LHash(LList<K, V>[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер хеш-таблицы должен быть больше 0.");
        }
        this.size = array.length;
        this.array = Arrays.copyOf(array, array.length);
    }

    /**
     * Получает размер хеш-таблицы.
     * @return размер хеш-таблицы.
     */
    public int getSize() {
        return size;
    }

    /**
     * Получает хеш-таблицу.
     * @return хеш-таблицу.
     */
    public LList<K, V>[] getHash() {
        return array;
    }

    @Override
    public int hashFunc(K key) {
        int hashValue = 0;
        for(int j = 0; j < key.toString().length(); ++j) {
            int letter = key.toString().charAt(j);
            // метода Горнера: (((var4*n + var3)*n + var2)*n + var1)*n + var0
            hashValue = (hashValue * j + letter) % size;
        }
        return hashValue;
    }

    /**
     * Инкрементирует целочисленные данные (счетчик) для заданного ключа элемента кеш-таблицы.
     * Для нецелочисленных данных инкремент игнорируется и данные при повторе ключа не меняются.
     * Уникальность элементов по ключу {@code K} поддерживается.
     * @param key ключ элемента.
     * @param value данные элемента как величина инкремента счетчика.
     */
    public void inc(K key, V value) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new LList<>();
        }
        array[hashValue].inc(new LNode<>(key, value));
    }

    /**
     * Помещает заданные ключ и данные элемента в хеш-таблицу.
     * Данные при повторе ключа переписываются.
     * Уникальность элементов по ключу {@code K} поддерживается.
     * @param key ключ элемента хеш-таблицы.
     * @param value данные элемента хеш-таблицы.
     */
    public void put(K key, V value) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new LList<>();
        }
        array[hashValue].insert(new LNode<>(key, value), false);
    }

    /**
     * Получает данные по заданному ключу элемента хеш-таблицы.
     * @param key ключ элемента хеш-таблицы.
     * @return данные элемента хеш-таблицы.
     */
    public V get(K key) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            return null;
        }
        LNode<K, V> current = array[hashValue].getFirst();
        while (current != null && !current.getKey().equals(key)) {
            current = current.getNext();
        }
        if (current != null) {
            return current.getValue();
        }
        return null;
    }

    /**
     * Копирует массив связанных списков хеш-таблицы.
     * @return результирующий массив (тип ключа и тип данных элементов списков сохраняется).
     */
    public LList<K, V>[] copy() {
        return Arrays.copyOf(array, size);
    }

    /**
     * Копирует массив связанных списков хеш-таблицы с преобразованием типов ключа и данных элементов списков в тип {@code String}.
     * @return новый массив с типом ключа и данных элементов списков {@code String}.
     */
    public LList<String, String>[] toStringCopy() {
        LList<String, String>[] newArray = new LList[size];
        for(int j = 0; j < size; ++j) {
            if(array[j] != null) {
                LList<String, String> newList = new LList<>();
                LNode<K, V> curNode = array[j].getFirst();
                while (curNode != null) {
                    newList.add(new LNode<>(curNode.getKey().toString(), curNode.getValue().toString()));
                    curNode = curNode.getNext();
                }
                newArray[j] = newList;
            }
        }
        return newArray;
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Инициатор выводит свои верхнее и нижнее оформления сообщения и отключает их вывод в цепочке объектов.
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        for(int j = 0; j < array.length; ++j) {
            if(array[j] != null) {
                out.println("");
                out.print(j + ": ");
                array[j].display(out);
            }
        }
        out.println("");
        out.println(footer);
    }
}
