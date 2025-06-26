package treeQ;

import priorityQ.PriorityQ;

import java.util.Stack;

public class TreeQ {
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
    public void displayTree() {
        Stack<NodeQ> global = new Stack();
        global.push(root);
        int nBlanks = 64;
        boolean isRowEmpty = false;
        System.out.println("...................................................");
        while(isRowEmpty == false) {
            isRowEmpty = true;
            Stack<NodeQ> local = new Stack();
            for (int j = 0; j < nBlanks - 2; ++j) {
                System.out.print(" ");
            }
            while(global.isEmpty() == false) {
                NodeQ current = (NodeQ) global.pop();
                if (current != null) {
                    isRowEmpty = false;
                    local.push(current.getNodeLeftChild());
                    local.push(current.getNodeRightChild());
                    String sdt = current.getNodeSData();
                    if(sdt.equals(PriorityQ.unixNewRow)) {
                        sdt = "\\n";
                    }
                    System.out.print(" " + current.getNodeIData() + sdt + " ");
                } else {
                    local.push(null);
                    local.push(null);
                    System.out.print(" -- ");
                }
                for (int j = 0; j < nBlanks * 2 - 4; ++j) {
                    System.out.print(" ");
                }
            }
            nBlanks /= 2;
            System.out.println("");
            while (local.isEmpty() == false) {
                global.push(local.pop());
            }
        }
        System.out.println("...................................................");
    }
    public boolean insert(int it, String st) {
        return insert(it, st, false);
    }
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
