package priorityQ;

import hashTable.HashTable;
import listQ.LinkQ;
import listQ.ListQ;
import listTreeQ.LinkT;
import listTreeQ.ListT;
import treeQ.NodeQ;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;

public class PriorityQ {
    private ListT list;
    private int nElem;
    private HashTable hash;
    private HashTable hashCharset;
    private int nMinLengthCharset;
    private String filePath;
    private final String delimiter = "<=>";

    public static final String windowsNewRow = "\r\n";
    public static final String unixNewRow = "\n";
    public PriorityQ() {
        list = new ListT();
    }
    public ListT getList() {
        return list;
    }
    public void hashFile(String path) throws IOException {
        Scanner in = new Scanner(Path.of(path));
        String text = "";
        while(in.hasNext()) {
            text += in.nextLine() + unixNewRow; // unixNewRow для однобайтовой кодировки новой строки
        }
        filePath = path;
        hash = new HashTable(101);
        nElem = text.length();
        for(int j = 0; j < nElem; ++j) {
            hash.inc(String.valueOf(text.charAt(j)));
        }
    }
    public void encodeFile(String fileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(filePath));
        String text = "";
        while(in.hasNext()) {
            text += in.nextLine() + unixNewRow; // unixNewRow для однобайтовой раскодировки новой строки
        }
        PrintWriter out = new PrintWriter(fileName, charset);
        for(int j = 0; j < text.length(); ++j) {
            out.print(hash.getCode(String.valueOf(text.charAt(j))));
        }
        out.flush();
    }
    public void displayHash() {
        hash.displayHash();
    }

    public void setListTree() {
        ListQ[] array = hash.getArray();
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
    public void setHuffmanTree() {
        while(list.getLength() > 1) {
            LinkT link1 = list.deleteRoot();
            LinkT link2 = list.deleteRoot();
            LinkT mrg = list.merge(link1, link2);
            list.insert(mrg);
        }
    }
    public void displayList() {
        list.displayList();
    }
    public void setCharset(NodeQ node, String code) {
        if(node == null) {
            return;
        }
        setCharset(node.getNodeLeftChild(), code + '0');
        int hashValue = hash.hashFunc(node.getNodeSData());
//        if(hash.array[hashValue] == null) {
//            System.out.println("setCharset 84: " + node.getNodeSData());
//        }
        if(hash.array[hashValue] != null) {
            LinkQ current = hash.array[hashValue].getLinkQRoot();
            while (current != null && current.getSData() != node.getNodeSData()) {
                current = current.getNextChild();
            }
            if (current != null) {
                current.setBCode(code);
            }
        }
        setCharset(node.getNodeRightChild(), code + '1');
    }
    public void saveCharsetToFile(String fileName, Charset charset) throws IOException {
        PrintWriter out = new PrintWriter(fileName, charset);
        for(int j = 0; j < hash.getArraySize(); ++j) {
            if(hash.array[j] != null) {
                LinkQ current = hash.array[j].getLinkQRoot();
                while (current != null) {
                    out.println(current.getSData() + delimiter + current.getBCode());
                    current = current.getNextChild();
                }
            }
        }
        out.flush();
    }
    public void loadCharsetFromFile(String fileName, Charset charset) throws IOException {
        Scanner in = new Scanner(Path.of(fileName));
        int size = 0;
        while(in.hasNext()) {
            size++;
            in.nextLine();
        }
        hashCharset = new HashTable(size * 2);
        int hashValue = 0;
        nMinLengthCharset = 0;
        in = new Scanner(Path.of(fileName));
        while(in.hasNext()) {
            String text = in.nextLine();
            // пустой символ при раскодировки считать символом новой строки
            if(text.equals("")) {
                // заменить его на символ новой строки, например, в формате Windows
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
//        hashCharset.displayHash();
    }
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
