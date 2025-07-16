package priorityQ;

import hashTable.HashTable;
import listQ.LinkQ;
import listQ.ListQ;
import listT.LinkT;
import listT.ListT;
import treeQ.NodeQ;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Приоритетная очередь {@code PriorityQ} для формирования дерева Хаффмана
 */
public class PriorityQ {
    /**
     * Связанный список {@code ListT} содержит элементы {@code LinkT} с использованием деревьев {@code TreeQ}
     */
    private ListT list;
    private int nElem;
    /**
     * Хеш-таблица {@code hashText} используется как частотная таблица символов кодируемого текста
     */
    private HashTable hashText;
    /**
     * Хеш-таблица {@code hashCharset} используется как набор символов кодировки
     */
    private HashTable hashCharset;
    private int nMinLengthCharset;
    private String filePath;
    private final String delimiter = "<=>";
    /**
     * Символы новой строки. Однобайтовый используется при кодировании. Многобайтовый используется при раскодировании.
     */
    public static final String windowsNewRow = "\r\n";
    public static final String unixNewRow = "\n";
    public PriorityQ() {
        list = new ListT();
    }
    public ListT getList() {
        return list;
    }
    /**
     * Проверяет является ли заданное значение простым числом
     */
    private boolean isPrime(int value) {
        for(int j = 2; j * j <= value; ++j) {
            if(value % j == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Возвращает первое простое число после заданного значения
     */
    private int getPrime(int min) {
        for(int j = min + 1; true; ++j) {
            if(isPrime(j)) {
                return j;
            }
        }
    }
    /**
     * Формирует частотную таблицу символов заданного файла {@code path}
     */
    public void hashFile(String path) throws IOException {
        Scanner in = new Scanner(Path.of(path));
        String text = "";
        while(in.hasNext()) {
            text += in.nextLine() + unixNewRow; // unixNewRow для однобайтовой кодировки новой строки
        }
        filePath = path;
        hashText = new HashTable(277);
        nElem = text.length();
        for(int j = 0; j < nElem; ++j) {
            hashText.inc(String.valueOf(text.charAt(j)));
        }
    }
    /**
     * Отображает частотную таблицу символов
     */
    public void displayHashText() {
        System.out.println("........................................");
        System.out.println("Хеш-таблица с символами текста hashText:");
        hashText.displayHash();
    }
    /**
     * Сохраняет в файле {@code fileName} ЗАкодированный текст
     */
    public void encodeFile(String fileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(filePath));
        String text = "";
        while(in.hasNext()) {
            text += in.nextLine() + unixNewRow; // unixNewRow для однобайтовой раскодировки новой строки
        }
        PrintWriter out = new PrintWriter(fileName, charset);
        for(int j = 0; j < text.length(); ++j) {
            out.print(hashText.getCode(String.valueOf(text.charAt(j))));
        }
        out.flush();
    }
    /**
     * Первичное заполнение приоритетной очереди деревьями с одним корневым узлом для каждого символа частотной таблицы символов.
     * Заполнение происходит на основе хеш-таблицы, заполненной по ключу - символ и значением - частотность символа.
     */
    public void setListTree() {
        ListQ[] array = hashText.getArray();
        for(int j = 0; j < array.length; ++j) {
            if (array[j] != null) {
                LinkQ current = array[j].getLinkQRoot();
                while (current != null) {
                    LinkT link = new LinkT();
                    link.insert(current.getIData(), current.getSData());
                    list.insertSorted(link);
                    current = current.getNextChild();
                }
            }
        }
    }
    /**
     * Формирование дерево Хаффмана после первичного заполнения приоритетной очереди {@code setListTree}
     */
    public void setHuffmanTree() {
        while(list.getLength() > 1) {
            LinkT link1 = list.deleteRoot();
            LinkT link2 = list.deleteRoot();
            LinkT mrg = list.merge(link1, link2);
            list.insert(mrg);
        }
    }
    /**
     * Отображение дерево Хаффмана
     */
    public void displayHuffmanTree() {
        list.displayList();
    }
    /**
     * Формирование набор символов кодировки
     */
    public void setCharset(NodeQ node, String code) {
        if(node == null) {
            return;
        }
        setCharset(node.getNodeLeftChild(), code + '0');
        int hashValue = hashText.hashFunc(node.getNodeSData());
        if(hashText.array[hashValue] != null) {
            LinkQ current = hashText.array[hashValue].getLinkQRoot();
            while (current != null && current.getSData() != node.getNodeSData()) {
                current = current.getNextChild();
            }
            if (current != null) {
                current.setBCode(code);
            }
        }
        setCharset(node.getNodeRightChild(), code + '1');
    }
    /**
     * Сохранение набора символов кодировки в файле {@code fileName}
     */
    public void saveCharsetToFile(String fileName, Charset charset) throws IOException {
        PrintWriter out = new PrintWriter(fileName, charset);
        for(int j = 0; j < hashText.getArraySize(); ++j) {
            if(hashText.array[j] != null) {
                LinkQ current = hashText.array[j].getLinkQRoot();
                while (current != null) {
                    out.println(current.getSData() + delimiter + current.getBCode());
                    current = current.getNextChild();
                }
            }
        }
        out.flush();
    }
    /**
     * Чтение из файла {@code fileName} набора символов кодировки
     */
    public void loadCharsetFromFile(String fileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(fileName));
        int size = 0;
        while(in.hasNext()) {
            size++;
            in.nextLine();
        }
        hashCharset = new HashTable(getPrime(size * 2));
        int hashValue = 0;
        nMinLengthCharset = 0;
        in = new Scanner(Path.of(fileName));
        while(in.hasNext()) {
            String text = in.nextLine();
            // пустой символ при раскодировании считать символом новой строки
            if(text.equals("")) {
                // заменить его на реальный символ новой строки, например, в формате Windows
                text += PriorityQ.windowsNewRow + in.nextLine();
            }
            String[] arr = text.split(delimiter);
            if(arr.length > 1) {
                hashCharset.putCode(arr[1], arr[0]);
                if(nMinLengthCharset == 0) {
                    nMinLengthCharset = arr[1].length();
                }
                if (arr[1].length() < nMinLengthCharset) {
                    nMinLengthCharset = arr[1].length();
                }
            }
        }
    }
    /**
     * Отображение набора символов кодировки
     */
    public void displayHashCharset() {
        System.out.println(".....................................................");
        System.out.println("Хеш-таблица с набором символов кодировки hashCharset:");
        hashCharset.displayHash();
    }
    /**
     * Чтение из файла {@code decodeFileName} ЗАкодированного текста и РАСкодирование его в файл {@code encodeFileName}
     */
    public void decodeFile(String decodeFileName, String encodeFileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(decodeFileName));
        String text = in.nextLine();
        PrintWriter out = new PrintWriter(encodeFileName, charset);
        while(text.length() > 0) {
            int offset = nMinLengthCharset;
            String code = text.substring(0, offset);
            while(hashCharset.getCode(code) == null) {
                offset++;
                if (offset > 10) {
                    System.out.println("Out of length !!!");
                    return;
                }
                code = text.substring(0, offset);
            }
            out.print(hashCharset.getCode(code));
            text = text.substring(offset);
        }
        out.flush();
    }
}
