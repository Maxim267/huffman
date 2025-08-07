package huffman;

import binarySearchTree.BSNode;
import binarySearchTree.BSTree;
import utils.output.*;

/**
 * Дерево Хаффмана (Huffman tree).
 */
public class HfmTree implements IntOutput {
    /**
     * Бинарное дерево поиска.
     */
    private final BSTree<Integer, String> tree;

    /**
     * Поток вывода дерева Хаффмана.
     */
    public IntDisplay out;

    /**
     * Конструктор создает незаполненное дерево Хаффмана.
     */
    public HfmTree() {
        tree = new BSTree<>();
        setOutput();
    }

    /**
     * Установить поток вывода.
     */
    private void setOutput() {
        // Вывод в поток приоритетной очереди как массив
        String header = "<<< Дерево Хаффмана: ";
        String footer = ">>>";
        out = new IntDisplay(this::display, header, footer);
    }

    /**
     * Получить корневой узел дерева Хаффмана.
     * @return корневой узел дерева Хаффмана.
     */
    public BSNode<Integer, String> getHfmTreeRoot() {
        return tree.getRoot();
    }

    /**
     * Добавить узел в дерево с заданными данными и признаком объединенного узла.
     * @param key значение ключа узла.
     * @param value данные узла.
     * @param isMerge признак объединенного узла.
     * @return добавленный узел.
     */
    public BSNode<Integer, String> add(Integer key, String value, boolean isMerge) {
        return tree.add(key, value, isMerge);
    }

    /**
     * Добавить узел в дерево с заданными данными.
     * @param key значение ключа узла.
     * @param value данные узла.
     * @return добавленный узел.
     */
    public BSNode<Integer, String> add(Integer key, String value) {
        return tree.add(key, value, false);
    }


    // DISPLAY

    @Override
    public String toString() {
        return tree.getRoot().getKey().toString() + (tree.getRoot().getValue() != null ? "/" + tree.getRoot().getValue() : "") + " ";
    }

    @Override
    public void display(int blanks, DualOutput out) {
        // Родительские Заголовок и Нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        tree.display(blanks, out);
        out.println(footer);
    }
}
