package arrayHeap;

import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.util.Objects;

/**
 * Элемент кучи на массиве (Array element).
 * @param <K> тип ключей, поддерживаемых этим элементом кучи.
 * @param <V> тип соответствующих ключам данных.
 */
public class ANode<K extends Comparable<K>, V> implements Output {
    /**
     * Значение ключа элемента.
     */
    private K key;

    /**
     * Данные элемента.
     */
    private V value;

    /**
     * Поток вывода элемента.
     */
    public final Display out = new Display(this::display, "<<<Array element: ", ">>>");

    /**
     * Создает элемент с заданными ключом и данными.
     * @param key значение ключа элемента.
     * @param value данные элемента.
     */
    public ANode(K key, V value) {
        this.key = Objects.requireNonNull(key, "Значение ключа не должно быть null");
        this.value = value;
    }

    /**
     * Создает элемент с заданным ключом (с пустыми данными).
     * @param key значение ключа элемента.
     */
    public ANode(K key) {
        this(key, null);
    }

    /**
     * Сравнивает текущий и заданный элементы.
     * Обеспечивает естественный порядок элементов в куче.
     * @param other значение заданного элемента.
     * @return результат сравнения элементов:
     *         -1 - текущий элемент меньше заданного элемента;
     *          0 - элементы равны;
     *          1 - текущий элемент больше заданного элемента.
     */
    public int compareToOther(ANode<K, V> other) {
        return key.compareTo(other.getKey());
    }

    /**
     * Устанавливает заданное значение ключа элемента.
     * @param key заданное значение ключа.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Получает значение ключа элемента.
     * @return значение ключа.
     */
    public K getKey() {
        return key;
    }

    /**
     * Получает данные элемента.
     * @return данные элемента.
     */
    public V getValue() {
        return value;
    }


    // DISPLAY

    @Override
    public String toString() {
        return getKey().toString() + (getValue() != null ? "/" + getValue() : "");
    }

    @Override
    public void display(DualOutput out) {
        // Родительские верхнее и нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        out.print(this.toString());
        out.print(footer);
    }
}