package priorityQueue;

import arrayHeap.AHeap;
import arrayHeap.ANode;
import utils.output.*;

/**
 * Неограниченная приоритетная очередь (Priority Queue) основанная на приоритетной куче (min-Heap).
 * Куча min-Heap обеспечивает доступ к элементу с наименьшим приоритетом.
 * Элементы упорядочены в соответствии с естественным порядком их ключей {@code K}.
 * Уникальность элементов не поддерживается.
 * @param <K> тип ключей, поддерживаемых этой очередью.
 * @param <V> тип соответствующих ключам данных.
 */
public class PQueue<K  extends Comparable<K>, V> implements Output, IntOutput {
    /**
     * Приоритетная куча
     */
    private final AHeap<K, V> heap;

    /**
     * Интерфейс вывода в поток приоритетной очереди как массив.
     */
    public final Display out = new Display(this::display, "<<< Priority Queue (as array): ", ">>>");
    /**
     * Интерфейс вывода в поток приоритетной очереди как дерево.
     */
    public final IntDisplay outTree = new IntDisplay(this::display, "<<< Priority Queue (as tree): ", ">>>");

    /**
     * Создает пустую приоритетную очередь с реализацией на куче с доступом к элементу с наименьшим приоритетом.
     */
    public PQueue() {
        heap = new AHeap<>(false);
    }

    /**
     * Получает данные первого элемента очереди.
     * @return первый элемент очереди.
     */
    public V getFirstValue() {
        if(heap.getArrayNode(0) == null) {
            return null;
        }
        return heap.getArrayNode(0).getValue();
    }

    /**
     * Вставляет элемент в приоритетную очередь без данных.
     * Уникальность элементов не поддерживается.
     * @param key ключ элемента.
     */
    public void add(K key) {
        heap.insert(key);
    }

    /**
     * Вставляет элемент в приоритетную очередь с данными.
     * Уникальность элементов не поддерживается.
     * @param key ключ элемента.
     * @param value данные элемента.
     */
    public void add(K key, V value) {
        heap.insert(key, value);
    }

    /**
     * Извлекает первый элемент очереди или возвращает null, если очередь пуста.
     * @return первый элемент очереди.
     */
    public ANode<K, V> poll() {
        return heap.remove();
    }

    /**
     * Получает размер очереди.
     * @return размер очереди.
     */
    public int size() {
        return heap.size();
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Инициатор выводит свои верхнее и нижнее оформления сообщения и отключает их вывод в цепочке объектов.
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        heap.display(out);
        out.println(footer);
    }

    @Override
    public void display(int blanks, DualOutput out) {
        // Инициатор выводит свои верхнее и нижнее оформления сообщения и отключает их вывод в цепочке объектов.
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        heap.display(blanks, out);
        out.println(footer);
    }
}
