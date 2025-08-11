package linkedList;

import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

/**
 * Односвязный список с хвостовым указателем (Tail-Pointer Singly Linked List).
 * В зависимости от применяемых методов элементы упорядочиваются в естественном порядке их ключей {@code K} (поддерживается уникальность ключей) или в порядке вставки элементов (не поддерживается уникальность).
 * @param <K> тип ключей, поддерживаемых этим списком.
 * @param <V> тип соответствующих ключам данных.
 */
public class LList<K extends Comparable<K>, V> implements Output {
    /**
     * Первый элемент списка.
     */
    private LNode<K, V> first;

    /**
     * Последний элемент списка.
     */
    private LNode<K, V> last;

    /**
     * Размер списка (количество элементов).
     */
    private int size;

    /**
     * Интерфейс вывода в поток односвязного списка в поток.
     */
    public final Display out = new Display(this::display, "<<< Linked List: ", ">>>");

    /**
     * Создает пустой связанный список.
     */
    public LList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Получает размер списка.
     * @return количество элементов списка.
     */
    public int size() {
        return size;
    }

    /**
     * Получает элемент списка по заданному ключу.
     * @param key заданный ключ.
     * @return элемент списка.
     */
    public LNode<K, V> get(K key) {
        LNode<K, V> current = getFirst();
        while (current != null && !current.getValue().toString().equals(key.toString())) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Получает первый элемент списка.
     * @return первый элемент списка.
     */
    public LNode<K, V> getFirst() {
        return first;
    }

    /**
     * Добавляет элемент в конец списка.
     * Элементы упорядочиваются с использованием порядка вставки.
     * Уникальность элементов не поддерживается.
     * @param node новый элемент.
     */
    public void add(LNode<K, V> node) {
        if(first == null) {
            first = node;
            last = node;
        }
        else {
            last.setNext(node);
            last = node;
        }
        size++;
    }

    /**
     * Вставляет элемент в список с инкрементальным изменением целочисленных данных {@code V} при совпадении ключа {@code K}.
     * Элементы упорядочиваются по ключу {@code K} с использованием естественного порядка.
     * Уникальность элементов по ключу {@code K} поддерживается.
     * @param node новый элемент.
     */
    public void inc(LNode<K, V> node) {
        insert(node, true);
    }

    /**
     * Вставляет элемент в список с переписыванием данных {@code V} при совпадении ключа {@code K}.
     * Элементы упорядочиваются по ключу {@code K} с использованием естественного порядка.
     * Уникальность элементов по ключу {@code K} поддерживается.
     * @param node новый элемент.
     */
    public void insert(LNode<K, V> node) {
        insert(node, false);
    }

    /**
     * Вставляет элемент в список.
     * Элементы упорядочиваются по ключу {@code K} с использованием естественного порядка.
     * Уникальность элементов по ключу {@code K} поддерживается.
     * @param node новый элемент
     * @param isInc признак инкремента для целочисленных данных {@code V}:
     *              Если {@code isInc} = true, то целочисленные данные {@code V} увеличиваются на заданное значение;
     *              Если {@code isInc} = false, то данные {@code V} перезаписываются на заданное значение.
     */
    public void insert(LNode<K, V> node, boolean isInc) {
        if(first == null) {
            first = node;
            last = node;
        }
        else {
            LNode<K, V> current = first;
            LNode<K, V> parent = null;
            while(current != null && node.compareToOther(current) >= 0) {
                // Поддержать уникальность
                if(node.compareToOther(current) == 0) {
                    if(isInc) {
                        if((node.getValue() instanceof Integer nodeVal)
                        && (current.getValue() instanceof Integer currVal)) {
                            // Инкрементировать данные
                            Integer sum = nodeVal + currVal;
                            current.setValue((V) sum);
                        }
                    }
                    else {
                        // Переписать данные
                        current.setValue(node.getValue());
                    }
                    return;
                }
                parent = current;
                current = current.getNext();
            }
            // Если список пустой или если заданный ключ меньше первого ключа в списке
            if(parent == null) {
                // Добавить новый элемент в начало списка
                node.setNext(first);
                first = node;
            }
            // Заданный ключ больше последнего ключа в списке
            else if(current == null) {
                // Добавить новый элемент в конец списка
                parent.setNext(node);
                last = node;
            }
            // Заданный ключ меньше следующего ключа в списке (и больше предыдущего)
            else {
                // Добавить новый элемент в диапазон списка
                node.setNext(current);
                parent.setNext(node);
            }
        }
        size++;
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Инициатор выводит свои верхнее и нижнее оформления сообщения и отключает их вывод в цепочке объектов.
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        LNode<K, V> current = first;
        while(current != null) {
            current.display(out);
            current = current.getNext();
        }
        out.print(footer);
    }
}
