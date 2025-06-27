package treeQ;

/**
 * Узел {@code NodeQ} дерева {@code TreeQ}
 */
public class NodeQ {
    /**
     * Ключевое поле узла.
     * Используется для частотности символов кодируемого текста
     */
    private int iData;
    /**
     * Строковое значение узла.
     * Используется для символа кодируемого текста
     */
    private String sData;
    private boolean isMerge;
    private NodeQ leftChild;
    private NodeQ rightChild;
    public NodeQ(int id, String sd, boolean merge) {
        iData = id;
        sData = sd;
        isMerge = merge;
    }
    public int getNodeIData() {
        return iData;
    }
    public String getNodeSData() {
        return sData;
    }
    public NodeQ getNodeLeftChild() {
        return leftChild;
    }
    public void setNodeLeftChild(NodeQ left) {
        leftChild = left;
    }
    public NodeQ getNodeRightChild() {
        return rightChild;
    }
    public void setNodeRightChild(NodeQ right) {
        rightChild = right;
    }
    public boolean getIsMerge() {
        return isMerge;
    }
}
