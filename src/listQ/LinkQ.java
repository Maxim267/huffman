package listQ;

public class LinkQ {
    private int iData;
    private String sData;
    private String bCode;
    private LinkQ nextChild;
    LinkQ priorChild;
    public LinkQ(int idt, String sdt, String bdt) {
        iData = idt;
        sData = sdt;
        bCode = bdt;
    }
    public String getSData() {
        return sData;
    }
    public void setBCode(String code) {
        bCode = code;
    }
    public String getBCode() {
        return bCode;
    }
    public LinkQ getNextChild() {
        return nextChild;
    }
    public void setNextChild(LinkQ next) {
        nextChild = next;
    }
    public void displayNode() {
        System.out.print("{");
        System.out.print(iData + " " + sData + "(" + bCode + ")");
        System.out.print("}");
    }
    public int getIData() {
        return iData;
    }
    public void setData(int value) {
        iData = value;
    }
    public void incIData() {
        iData++;
    }
}
