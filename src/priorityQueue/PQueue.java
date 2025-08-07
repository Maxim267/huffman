package priorityQueue;

import arrayHeap.AHeap;
import arrayHeap.ANode;
import utils.output.*;

/**
 * Неограниченная приоритетная очередь (Priority Queue) основанная на приоритетной куче (min-Heap).
 * Куча min-Heap обеспечивает доступ к элементу с наименьшим приоритетом.
 * Элементы упорядочиваются с использованием их естественного порядка.
 * Уникальность элементов не поддерживается.
 * Тип параметров:
 * @param <K> тип ключей, поддерживаемых этой очередью.
 * @param <V> тип соответствующих ключам данных.
 */
public class PQueue<K  extends Comparable<K>, V> implements Output, IntOutput {
    /**
     * Приоритетная куча
     */
    private final AHeap<K, V> heap;

    /**
     * Поток вывода приоритетной очереди как массив.
     */
    public Display out;
    /**
     * Поток вывода приоритетной очереди как дерево.
     */
    public IntDisplay outTree;

    /**
     * Конструктор создает новую приоритетную очередь с реализацией на куче с доступом к элементу с наименьшим приоритетом.
     */
    public PQueue() {
        heap = new AHeap<>(false);
        setOutput();
    }

    /**
     * Установить поток вывода.
     */
    private void setOutput() {
        // Вывод в поток приоритетной очереди как массив
        String header = "<<< Priority Queue (as array): ";
        String footer = ">>>";
        out = new Display(this::display, header, footer);

        // Вывод в поток приоритетной очереди как дерево
        header = "<<< Priority Queue (as tree): ";
        outTree = new IntDisplay(this::display, header, footer);
    }

    /**
     * Получить данные первого элемента очереди.
     * @return первый элемент очереди.
     */
    public V getFirstValue() {
        if(heap.getArrayNode(0) == null) {
            return null;
        }
        return heap.getArrayNode(0).getValue();
    }

    /**
     * Вставить элемент в приоритетную очередь без данных.
     * Уникальность элементов не поддерживается.
     * @param key ключ элемента.
     */
    public void add(K key) {
        heap.insert(key);
    }

    /**
     * Вставить элемент в приоритетную очередь с данными.
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
     * Получить размер очереди.
     * @return размер очереди.
     */
    public int size() {
        return heap.size();
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        heap.display(out);
        out.println(footer);
    }

    @Override
    public void display(int blanks, DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        heap.display(blanks, out);
        out.println(footer);
    }
}
