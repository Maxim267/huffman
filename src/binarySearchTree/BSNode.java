package binarySearchTree;

import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.util.Objects;

/**
 * Узел двоичного дерева (BS Tree node)
 * @param <K> тип ключей, поддерживаемых этим узлом дерева.
 * @param <V> тип соответствующих ключам данных.
 */
public class BSNode<K extends Comparable<K>, V> implements Output {
    /**
     * Значение ключа узла.
     */
    private K key;

    /**
     * Данные узла.
     */
    private V value;

    /**
     * Признак объединенного узла.
     */
    private boolean isMerge;

    /**
     * Ссылка на левый дочерний узел.
     */
    private BSNode<K, V> leftChild;

    /**
     * Ссылка на правый дочерний узел.
     */
    private BSNode<K, V> rightChild;

    /**
     * Поток вывода узла.
     */
    public Display out;

    /**
     * Конструктор создает новый узел с заданным значением ключа без данных.
     * @param key значение ключа.
     */
    public BSNode(K key) {
        this.key = Objects.requireNonNull(key, "Значение ключа не должно быть null");
        this.value = null;
        this.isMerge = false;
        setOutput();
    }

    /**
     * Конструктор создает новый узел с заданными значением ключа и данными.
     * @param key значение ключа узла.
     * @param value данные узла.
     */
    public BSNode(K key, V value) {
        this.key = Objects.requireNonNull(key, "Значение ключа не должно быть null");
        this.value = value;
        this.isMerge = false;
        setOutput();
    }

    /**
     * Установить поток вывода.
     */
    private void setOutput() {
        String header = "<<<Tree node: ";
        String footer = ">>>";
        out = new Display(this::display, header, footer);
    }

    /**
     * Сравнить текущий и заданный узлы.
     * Обеспечивает естественный порядок узлов в дереве.
     * @param other заданный узел.
     * @return значение сравнения узлов:
     *         -1 - текущий узел меньше заданного узла,
     *          0 - узлы равны,
     *          1 - текущий узел больше заданного узла.
     */
    public int compareToOther(BSNode<K, V> other) {
        return key.compareTo(other.getKey());
    }

    /**
     * Сравнить значения ключа текущего узла и заданного ключа.
     * Обеспечивает естественный порядок узлов в дереве.
     * @param other заданное значение ключа
     * @return значение сравнения ключей:
     *         -1 - текущий ключ меньше заданного ключа,
     *          0 - ключи равны,
     *          1 - текущий ключ больше заданного ключа.
     */
    public int compareToOther(K other) {
        return key.compareTo(other);
    }

    /**
     * Установить значение ключа узла.
     * @param key значение ключа узла.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Получить значение ключа узла.
     * @return значение ключа узла.
     */
    public K getKey() {
        return key;
    }

    /**
     * Получить данные узла.
     * @return данные узла.
     */
    public V getValue() {
        return value;
    }

    /**
     * Установить данные узла.
     * @param value данные узла.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Установить признак объединенного узла.
     * @param isMerge значение признака объединенного узла.
     */
    public void setIsMerge(boolean isMerge) {
        this.isMerge = isMerge;
    }

    /**
     * Получить значение признака объединенного узла.
     * @return значение признака объединенного узла.
     */
    public boolean getIsMerge() {
        return isMerge;
    }

    /**
     * Получить ссылку на левый дочерний узел.
     * @return узел.
     */
    public BSNode<K, V> getLeftChild() {
        return leftChild;
    }

    /**
     * Установить левый дочерний узел.
     * @param left узел.
     */
    public void setLeftChild(BSNode<K, V> left) {
        leftChild = left;
    }

    /**
     * Получить ссылку на правый дочерний узел.
     * @return узел.
     */
    public BSNode<K, V> getRightChild() {
        return rightChild;
    }

    /**
     * Установить правый дочерний узел.
     * @param right узел.
     */
    public void setRightChild(BSNode<K, V> right) {
        rightChild = right;
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
        out.print(this.toString());
        out.print(footer);
    }
}
