package listTreeQ;

import treeQ.TreeQ;

public class LinkT {
    private TreeQ tree;
    private LinkT nextChild;
    private LinkT priorChild;
    public LinkT() {
        tree = null;
    }
    public void insert(int it, String st) {
        if(tree == null) {
            tree = new TreeQ();
        }
        tree.insert(it, st);
    }
    public void displayLinkT() {
        tree.displayTree();
    }
    public TreeQ getLinkTTree() {
        return tree;
    }
    public void setLinkTTree(TreeQ linkTree) {
        tree = linkTree;
    }
    public LinkT getNextChild() {
        return nextChild;
    }
    public void setNextChild(LinkT next) {
        nextChild = next;
    }
    public void setPriorChild(LinkT prior) {
        priorChild = prior;
    }
}
