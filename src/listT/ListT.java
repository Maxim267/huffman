package listT;

import treeQ.TreeQ;

/**
 * Связанный список {code ListT} для приоритетной очереди {code PriorityQ}
 */
public class ListT {
    private LinkT root;
    private LinkT lastNode;
    private int length;
    public ListT() {
        root = null;
    }
    public int getLength() {
        return length;
    }
    public LinkT getLinkTRoot() {
        return root;
    }
    public void displayList() {
        LinkT temp = root;
        while(temp != null) {
            temp.displayLinkT();
            temp = temp.getNextChild();
        }
//        System.out.println();
    }
    /**
     * Добавить элемент в конец списка
     */
    public void insert(LinkT link) {
        if(root == null) {
            root = link;
            lastNode = link;
        }
        else {
            link.setPriorChild(lastNode);
            lastNode.setNextChild(link);
            lastNode = link;
        }
        length++;
    }
    /**
     * Добавить элемент в список с применением сортировки
     */
    public void insertSorted(LinkT link) {
        if(root == null) {
            root = link;
            lastNode = link;
        }
        else {
            LinkT current = root;
            LinkT parent = null;
            while(current != null && link.getLinkTTree().getTreeRoot().getNodeIData() > current.getLinkTTree().getTreeRoot().getNodeIData()) {
                parent = current;
                current = current.getNextChild();
            }
            if(parent == null) {
                link.setNextChild(root);
                root = link;
            }
            else if(current == null) {
                parent.setNextChild(link);
                lastNode = link;
            }
            else {
                link.setNextChild(current);
                parent.setNextChild(link);
            }
        }
        length++;
    }
    /**
     * Удалить элемент из начала списка
     */
    public LinkT deleteRoot() {
        LinkT current = root;
        if(root == null) {
            return null;
        }
        if (root.getNextChild() == null) {
            lastNode = null;
            root = null;
        }
        else {
            root = root.getNextChild();
        }
        length--;
        return current;
    }
    /**
     * Получить новый объединенный элемент из двух заданных элементов списка
     */
    public LinkT merge(LinkT left, LinkT right) {
        TreeQ mrg = new TreeQ();
        mrg.insert(left.getLinkTTree().getTreeRoot().getNodeIData() + right.getLinkTTree().getTreeRoot().getNodeIData(), "@", true);
        mrg.getTreeRoot().setNodeLeftChild(left.getLinkTTree().getTreeRoot());
        mrg.getTreeRoot().setNodeRightChild(right.getLinkTTree().getTreeRoot());
        LinkT link = new LinkT();
        link.setLinkTTree(new TreeQ());
        link.getLinkTTree().setTreeRoot(mrg.getTreeRoot());
        return link;
    }

}
