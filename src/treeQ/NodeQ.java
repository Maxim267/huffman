package treeQ;

public class NodeQ {
    private int iData;
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
