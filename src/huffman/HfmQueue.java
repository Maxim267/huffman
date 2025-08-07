package huffman;

import arrayHeap.ANode;
import binarySearchTree.BSNode;
import hashTable.LHash;
import linkedList.LList;
import linkedList.LNode;
import priorityQueue.PQueue;
import utils.output.*;

/**
 * Приоритетная очередь для формирования дерева Хаффмана (Huffman Priority Queue).
 */
public class HfmQueue implements Output, IntOutput {
    /**
     * Приоритетная очередь.
     */
    private PQueue<Integer, HfmTree> queue;

    /**
     * Поток вывода приоритетной очереди как массив.
     */
    public Display out;
    /**
     * Поток вывода приоритетной очереди как дерево.
     */
    public IntDisplay outTree;

    /**
     * Конструктор создает незаполненную приоритетную очередь.
     */
    public HfmQueue() {
        queue = new PQueue<>();
        setOutput();
    }

    /**
     * Установить вывод в поток.
     */
    private void setOutput() {
        // Вывод в поток приоритетной очереди как массив
        String header = "<<< Приоритетная очередь для формирования дерева Хаффмана (как массив): ";
        String footer = ">>>";
        out = new Display(this::display, header, footer);

        // Вывод в поток приоритетной очереди как дерево
        header = "<<< Приоритетная очередь для формирования дерева Хаффмана (как дерево): ";
        outTree = new IntDisplay(this::display, header, footer);
    }

    /**
     * Первичное заполнение приоритетной очереди деревьями.
     * Очередь заполняется деревьями с одним корневым узлом для каждого символа частотной таблицы символов.
     * Заполнение каждого корневого узла происходит парными значениями: ключ - это частотность символа; данные - это символ.
     * @param hashText предварительно заполненная частотная таблица символов на основе хеш-таблицы.
     *                 Частотная таблица содержит парные значения: ключ - это символ текста и данные - частотность символа.
     */
    public void setPrimaryQueue(LHash<String, Integer> hashText) {
        LList<String, Integer>[] array = hashText.getHash();
        for (LList<String, Integer> list : array) {
            if (list != null) {
                LNode<String, Integer> current = list.getFirst();
                while (current != null) {
                    // Создать дерево Хаффмана
                    HfmTree huffTree = new HfmTree();
                    // Добавить в дерево корневой узел: ключ - это частотность символа; данные - это символ
                    huffTree.add(current.getValue(), current.getKey());
                    // Добавить в очередь парное значение: ключ - это частотность символа; данные - это дерево Хаффмана с одним корневым узлом
                    queue.add(current.getValue(), huffTree);
                    current = current.getNext();
                }
            }
        }
    }

    /**
     * Объединить два заданных узла очереди.
     * Новый объединенный узел содержит новое объединенное дерево Хаффмана, у которого корневой узел имеет потомков на основе заданных узлов.
     * @param node1 первый узел
     * @param node2 второй узел
     * @return новый родительский узел с объединенным деревом Хаффмана
     */
    public ANode<Integer, HfmTree> mergeNodes(ANode<Integer, HfmTree> node1, ANode<Integer, HfmTree> node2) {
        HfmTree huffTree = new HfmTree();
        // Значение ключа нового узла формируется как сумма значений ключей двух заданных узлов
        BSNode<Integer, String> root = huffTree.add(node1.getKey() + node2.getKey(), "@", true);
        // Заданные узлы становятся потомками нового узла
        root.setLeftChild(node1.getValue().getHfmTreeRoot()); //getHfmTree().getRoot());
        root.setRightChild(node2.getValue().getHfmTreeRoot());
        // Создать новый родительский узел с данными на основе Дерева Хаффмана
        ANode<Integer, HfmTree> newNode = new ANode<>(root.getKey(), huffTree);
        return newNode;
    }

    /**
     * Формировать дерево Хаффмана.
     * В результате в очереди остается единственный узел содержащий объединенное дерево Хаффмана.
     * @return дерево Хаффмана.
     */
    public HfmTree createHfmTree() {
        // Пока очередь содержит больше одного элемента
        while(queue.size() > 1) {
            // Получить корневой узел из очереди
            ANode<Integer, HfmTree> node1 = queue.poll();
            // Еще раз получить корневой узел из очереди
            ANode<Integer, HfmTree> node2 = queue.poll();
            // Объединить эти два узла
            ANode<Integer, HfmTree> mrgNode = mergeNodes(node1, node2);
            queue.add(mrgNode.getKey(), mrgNode.getValue());
        }
        // В очереди остался единственный элемент содержащий объединенное дерево Хаффмана
        return queue.getFirstValue();
    }

    /**
     * Получить сформированное дерево Хаффмана.
     * Дерево формируется после последовательного запуска {@code setPrimaryQueue} и {@code setHuffmanTree}
     * @return дерево Хаффмана
     */
    public HfmTree getHfmTree() {
        return queue.getFirstValue();
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        queue.display(out);
        out.println(footer);
    }

    @Override
    public void display(int blanks, DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        queue.display(blanks, out);
        out.println(footer);
    }
}
