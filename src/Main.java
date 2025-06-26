import listQ.ListQ;
import listQ.LinkQ;
import priorityQ.PriorityQ;
import treeQ.NodeQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        // testing List
        ListQ list = new ListQ();
//        list.insert(1, "1");
//        list.insert(2, "2");
//        list.insert(3, "3");
//        list.insert(4, "4");

//        list.insertIDataSorted(4, "A");
//        list.insertIDataSorted(1, "Z");
        list.insertIData(4, "A");
        list.insertIData(1, "Z");

//        list.insertSDataSorted(1, "Z");
//        list.insertSDataSorted(4, "A");

        list.displayList();
        LinkQ temp = list.find("2");
        if(temp != null) {
            System.out.print("Found: ");
            temp.displayNode();
            System.out.println();
        }
//        list.delete("4");
//        list.delete("3");
//        list.delete("2");
//        list.delete("1");
//        list.displayList();

//        list.insertSorted(22, "22");
//        list.insertSorted(21, "21");
////        list.insertSorted(23, "23");
//        list.insertSorted(231, "23");
//        list.insert(24, "24");
//        list.insertSorted(241, "241", true);
//        list.insertSorted(241, "241", true);
//        list.displayList();
//        list.deleteRoot();
//        list.displayList();

//        int letter = ("abcde".charAt(0) - 96) * 27 * 27;
//        int letter = 2 << 5;
//        System.out.println(letter);

//        HashTable hash = new HashTable(101);
//        hash.put("G", 3);
//        hash.put("A", 1);
//        hash.put("A", 11);
//
//        hash.displayHash();
//        hash.inc("A");
//        hash.inc("G");
////        hash.displayHash();
//        hash.inc("Hello world");
//        hash.inc("Hello2 world");  hash.inc("Hello2 world");
//        hash.inc("Hello2 world Hello2 world");
//        hash.inc("Hello3 world Hello3 world");
//        hash.displayHash();
//        hash.inc("Hello2 world Hello2 world");
//        hash.displayHash();
//
//
        // read from file // C:\wrk\Java\Alg\huffman\src
//        Scanner in = new Scanner(Path.of("src\\Test.txt"));
//        String text = in.nextLine();
//        System.out.println(text);
//
//        HashTable hash = new HashTable(text.length());

//        hash.put("G", 3);
//        hash.put("A", 1);
//        hash.put("A", 11);
//
//        for(int j = 0; j < text.length(); ++j) {
//            hash.inc(String.valueOf(text.charAt(j)));
//        }
//        hash.displayHash();

//        TreeQ tree = new TreeQ();
//        tree.insert(50, "E");
//        tree.insert(45, "F");
//        tree.insert(75, "J");
//        tree.displayTree();

        // -------------
//        ListT list2 = new ListT();
//
//        LinkT link1 = new LinkT();
//        link1.insert(55, "D");
//        list2.insert(link1);
//
//        LinkT link2 = new LinkT();
//        link2.insert(57, "S");
//        list2.insert(link2);
//
//        list2.displayList();
//
//        list2.deleteRoot();
//        list2.displayList();


//        LinkT mrg = list2.merge(link1, link2);
//        list2.insert(mrg);
//        list2.displayList();

        PriorityQ pQueue = new PriorityQ();
        pQueue.hashFile("Test.txt");
//        pQueue.displayHash();
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        pQueue.setListTree();
//        pQueue.displayList();
        pQueue.setHuffmanTree();
        pQueue.displayList();

        NodeQ root = pQueue.getList().getLinkTRoot().getLinkTTree().getTreeRoot();
        pQueue.setCharset(root, "");
////        pQueue.displayHash();
//
        pQueue.encodeFile("EncodedText.txt", StandardCharsets.UTF_8);
        pQueue.saveCharsetToFile("Charset.txt", StandardCharsets.UTF_8);
//
        pQueue.loadCharsetFromFile("Charset.txt", StandardCharsets.UTF_8);
        pQueue.decodeFile("EncodedText.txt", "DecodedText.txt", StandardCharsets.UTF_8);
    }
}
