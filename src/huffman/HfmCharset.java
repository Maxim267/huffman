package huffman;

import binarySearchTree.BSNode;
import hashTable.LHash;
import linkedList.LNode;
import utils.calculations.MathUtils;
import utils.constants.AppConstants;
import utils.output.Display;
import utils.output.DualOutput;
import utils.output.Output;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Набор символов кодировки Хаффмана (Huffman Charset).
 */
public class HfmCharset implements Output {
    /**
     * Набор символов кодировки Хаффмана на основе хеш-таблицы.
     */
    private LHash<String, String> hash;

    /**
     * Корневой узел дерева Хаффмана.
     */
    private BSNode<Integer, String> root;

    /**
     * Минимальная длина выводимого значения кода Хаффмана .
     */
    private int minOffset;

    /**
     * Максимальная длина выводимого значения кода Хаффмана.
     */
    private int maxOffset;

    /**
     * Поток вывода хеш-таблицы с набором символов кодировки Хаффмана.
     */
    public final Display out = new Display(this::display, "<<< Huffman charset with hash: ", ">>>");

    /**
     * Поток вывода набора символов кодировки Хаффмана.
     */
    public final Display outHuffman = new Display(this::displayHuffman, "<<< Набор кодировки Хаффмана: ", ">>>");

    /**
     * Создает пустой набор символов кодировки Хаффмана.
     */
    public HfmCharset() {
        this.hash = new LHash<>(AppConstants.HASH_SIZE);
        this.root = null;
    }

    /**
     * Создает новый набор символов кодировки Хаффмана заполненный на основе заданного дерева Хаффмана.
     * @param tree дерево Хаффмана.
     */
    public HfmCharset(HfmTree tree) {
        hash = new LHash<>(AppConstants.HASH_SIZE);
        if(tree != null) {
            root = tree.getHfmTreeRoot();
            setCharset(root, "0");
        }
    }

    /**
     * Рекурсивно формирует набор символов кодировки Хаффмана.
     * Формирование начинается с корневого узла дерева Хаффмана.
     * @param node текущий узел дерева.
     * @param code текущий формируемый код Хаффмана.
     */
    public void setCharset(BSNode<Integer, String> node, String code) {
        if(node == null) {
            return;
        }
        // При переходе к левому потомку к формируемому коду добавить символ '0'
        setCharset(node.getLeftChild(), code + "0");

        if(!node.getIsMerge()) {
            hash.put(node.getValue(), code);
        }

        // При переходе к правому потомку к формируемому коду добавить символ '1'
        setCharset(node.getRightChild(), code + "1");
    }

    /**
     * Получает кеш-таблицу с набором символов кодировки Хаффмана.
     * @return хеш-таблицу.
     */
    public LHash<String, String> getHash() {
        return hash;
    }

    /**
     * Получает минимальную длину выводимого значения кода Хаффмана.
     * @return целочисленное значение.
     */
    public int getMinOffset() {
        return minOffset;
    }

    /**
     * Получает максимальную длину выводимого значения кода Хаффмана.
     * @return целочисленное значение.
     */
    public int getMaxOffset() {
        return maxOffset;
    }

    /**
     * Читает набор символов кодировки Хаффмана из файла.
     * @param fileName имя файла из которого читается набор символов кодировки Хаффмана.
     * @param charset имя стандартной кодировки символов файла, например, StandardCharsets.UTF_8.
     * @throws IOException если при открытии или создании файла произошла ошибка ввода-вывода.
     */
    public void readCharsetFromFile(String fileName, Charset charset) throws IOException {
        Path path = FileSystems.getDefault().getPath("", fileName);
        Scanner in = new Scanner(path, charset);
        int size = 0;
        while(in.hasNext()) {
            size++;
            in.nextLine();
        }
        hash = new LHash<>(AppConstants.HASH_SIZE);
        minOffset = 0;
        maxOffset = 0;
        in = new Scanner(Path.of(fileName));
        while(in.hasNext()) {
            String text = in.nextLine();
            // пустой символ при декодировании считать символом новой строки
            if(text.isEmpty()) {
                // заменить его на символ новой строки, например, в формате Windows
                text += AppConstants.WINDOWS_NEW_ROW + in.nextLine();
            }
            String[] arr = text.split(AppConstants.FILE_KEY_DELIMITER_VALUE);
            if(arr.length > 1) {
                hash.put(arr[1], arr[0]);
                if(minOffset == 0) {
                    minOffset = arr[1].length();
                }
                if (arr[1].length() < minOffset) {
                    minOffset = arr[1].length();
                }
                if(maxOffset < arr[1].length()) {
                    maxOffset = arr[1].length();
                }
            }
        }
    }


    // DISPLAY

    @Override
    public void display(DualOutput out) {
        // Родительские верхнее и нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.print(header);
        hash.display(out);
        out.println(footer);
    }

    /**
     * Выводит набор символов кодировки Хаффмана в заданный выходной поток.
     * @param out заданный поток вывода:
     *            - стандартный выходной поток (консоль) (использовать конструктор DualOutput());
     *            - вывод в файл (использовать конструктор DualOutput(String fileName)).
     */
    public void displayHuffman(DualOutput out) {
        // Родительские верхнее и нижнее сообщения отключают вывод дочерних сообщений
        String header = out.getHeader() != null ? out.getHeaderOnce() : this.out.getHeader();
        String footer = out.getFooter() != null ? out.getFooterOnce() : this.out.getFooter();

        out.println(header);
        for (int j = 0; j < hash.getSize(); ++j) {
            if (hash.getHash()[j] != null) {
                LNode<String, String> current = hash.getHash()[j].getFirst();
                while (current != null) {
                    out.println(current.getKey() + AppConstants.FILE_KEY_DELIMITER_VALUE + current.getValue());
                    current = current.getNext();
                }
            }
        }
        out.println(footer);
    }
}
