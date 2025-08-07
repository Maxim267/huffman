package linkedList;

import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.util.Objects;

/**
 * Элемент связанного списка (List node)
 * @param <K> тип ключей, поддерживаемых этим элементом списка.
 * @param <V> тип соответствующих ключам данных.
 */
public class LNode<K extends Comparable<K>, V> implements Output {
    /**
     * Значение ключа элемента.
     */
    private K key;

    /**
     * Данные элемента.
     */
    private V value;

    /**
     * Ссылка на следующий элемент.
     */
    private LNode<K, V> next;

    /**
     * Поток вывода элемента.
     */
    public Display out;

    /**
     * Конструктор создает новый элемент.
     * @param key ключ элемента.
     * @param value данные элемента.
     */
    public LNode(K key, V value) {
        this.key = Objects.requireNonNull(key, "Значение ключа не должно быть null");
        this.value = value;
        setOutput();
    }

    /**
     * Установить поток вывода.
     */
    private void setOutput() {
        String header = "<<<List node: ";
        String footer = ">>>";
        out = new Display(this::display, header, footer);
    }

    /**
     * Сравнить текущий и заданный элементы.
     * Обеспечивает естественный порядок элементов в списке.
     * @param other значение заданного элемента.
     * @return результат сравнения элементов: -1; 0; 1.
     */
    public int compareToOther(LNode<K, V> other) {
        return key.compareTo(other.getKey());
    }

    /**
     * Получить значение ключа элемента.
     * @return значение ключа элемента.
     */
    public K getKey() {
        return key;
    }

    /**
     * Получить данные элемента.
     * @return данные элемента.
     */
    public V getValue() {
        return value;
    }

    /**
     * Установить данные элемента.
     * @param value данные элемента.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Получить следующий элемент.
     * @return следующий элемент.
     */
    public LNode<K, V> getNext() {
        return next;
    }

    /**
     * Установить следующий элемент.
     * @param node следующий элемент.
     */
    public void setNext(LNode<K, V> node) {
        next = node;
    }


    // DISPLAY

    @Override
    public String toString() {
        return getKey().toString() + (getValue() != null ? "/" + getValue().toString() : "") + " ";
    }

    @Override
    public void display(DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        out.print(getKey().toString() + (getValue() != null ? "/" + getValue().toString() : "") + " ");
        out.print(footer);
    }
}
