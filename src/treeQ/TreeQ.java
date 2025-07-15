package treeQ;

import priorityQ.PriorityQ;
import java.util.Stack;

/**
 * Дерево {@code TreeQ}
 * используется для формирования дерева Хаффмана
 */
public class TreeQ {
    /**
     * Корневой узел {@code root}
     */
    private NodeQ root;
    public TreeQ() {
        root = null;
    }
    public NodeQ getTreeRoot() {
        return root;
    }
    public void getTreeRoot(NodeQ nodeRoot) {
        root = nodeRoot;
    }
    public void setTreeRoot(NodeQ treeNode) {
        root = treeNode;
    }
    /**
     * Получить количество уровней дерева
     */
    private int getNLevelTree() {
        NodeQ current = root;
        int nLevel = 0;
        while(current != null) {
            nLevel++;
            current = current.getNodeRightChild();
        }
        return nLevel;
    }
    /**
     * Вывести дерево в стандартный поток вывода
     */
    public void displayTree() {
        Stack<NodeQ> global = new Stack<>();
        global.push(root);
        int nLevel = getNLevelTree();
        int nBlanks = (int) Math.pow(2, nLevel);
        int len = 0;
        boolean isRowEmpty = false;
        System.out.println("<<< Дерево Хаффмана TreeQ:");
        while(!isRowEmpty) {
            isRowEmpty = true;
            Stack<NodeQ> local = new Stack<>();
            for (int j = 0; j < nBlanks; ++j) {
                System.out.print(" ");
            }
            while(!global.isEmpty()) {
                NodeQ current = global.pop();
                if (current != null) {
                    isRowEmpty = false;
                    local.push(current.getNodeLeftChild());
                    local.push(current.getNodeRightChild());
                    String sdt = current.getNodeSData();
                    if(sdt.equals(PriorityQ.unixNewRow)) {
                        sdt = "\\n";
                    }
                    System.out.print(current.getNodeIData() + sdt);
                    len = Integer.toString(current.getNodeIData()).length() + sdt.length();
                } else {
                    local.push(null);
                    local.push(null);
                    System.out.print("--");
                    len = 2;
                }
                for (int j = 0; j < nBlanks * 2 - (len); ++j) {
                    System.out.print(" ");
                }
            }
            nBlanks /= 2;
            System.out.println();
            while (!local.isEmpty()) {
                global.push(local.pop());
            }
        }
        System.out.println(">>>");
    }
    /**
     * Добавление узла с целочисленным ключом {@code it} и строковым значением {@code st}
     */
    public boolean insert(int it, String st) {
        return insert(it, st, false);
    }
    /**
     * Добавление узла с целочисленным ключом {@code it} и строковым значением {@code st}
     * с признаком того, что данный узел получен из объединения двух {@code merge}
     */
    public boolean insert(int it, String st, boolean merge) {
        NodeQ newNode = new NodeQ(it, st, merge);
        if(root == null) {
            root = newNode;
        }
        else {
            NodeQ current = root;
            NodeQ parent;
            while(current != null) {
                parent = current;
                if(it < current.getNodeIData()) {
                    current = current.getNodeLeftChild();
                    if(current == null) {
                        parent.setNodeLeftChild(newNode);
                        return true;
                    }
                }
                else {
                    if(it == current.getNodeIData()) {
                        return false;
                    }
                    current = current.getNodeRightChild();
                    if(current == null) {
                        parent.setNodeRightChild(newNode);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
