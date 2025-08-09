import arrayHeap.AHeap;
import binarySearchTree.BSTree;
import hashTable.LHash;
import linkedList.LList;
import linkedList.LNode;
import priorityQueue.PQueue;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// Пример использования базовых структур данных
public class Example {
    public void process() throws IOException {

        // Приоритетная очередь

        // PQueue   (Output, IntOutput)
        PQueue<Charset, Float> queue = new PQueue<>();
        queue.add(StandardCharsets.UTF_32, 5.0F);
        queue.add(StandardCharsets.UTF_16, 5.0F);
        queue.add(StandardCharsets.UTF_32BE, 5.0F);
        queue.add(StandardCharsets.UTF_16BE, 5.0F);
        queue.out.display();
//         queue.out.display("file1.txt");
        queue.outTree.display(32);
//        queue.outTree.display(32, "file2.txt");


        // Двоичное дерево поиска

        // BSTree     (IntOutput)
        BSTree<Integer, String> tree = new BSTree<>();
        tree.add(33, "ty");
        tree.add(5, "ya");
        tree.add(55, "dgha");
        tree.out.display(16);
//        tree.out.display(16, "file1.txt");


        // Приоритетная куча

        // AHeap     (Output, IntOutput)
        AHeap<Integer, String> heap = new AHeap<>();
        heap.insert(33, "rt");
        heap.insert(11, "gh");
        heap.insert(22, "rt");
        heap.out.display();
//         heap.out.display("file1.txt");
        heap.outTree.display(0);
//         heap.outTree.display(0, "file2.txt");


        // Хеш-таблица

        // LHash     (Output)
        LHash<Integer, String> hash = new LHash<>(7);
        hash.put(32, "re");
        hash.put(245, "re");
        hash.put(10, "or");
        hash.out.display();
//         hash.out.display("file1.txt");


        // Односвязный список

        // LList     (Output)
        LList<Integer, String> list = new LList<>();
        list.add(new LNode<>(3, "gf"));
        list.add(new LNode<>(1, "zx"));
        list.add(new LNode<>(2, "zx"));
        list.out.display();
//         list.out.display("file1.txt");

        }
}