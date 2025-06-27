package listQ;

/**
 * Связанный список ListQ для хранения данных в хеш-таблице {@code HashTable}
 */
public class ListQ {
    /**
     * Корневой элемент {@code root} связанного списка
     */
    private LinkQ root;
    private LinkQ lastNode;
    private int length;
    /**
     * Вид сортировки данных в списке {@code ListQ} соответствует сортировке по ключу в одноименных полях элемента списка {@code LinkQ}:
     * {@code sData}, {@code iData}, {@code bData}
     */
    public static enum sortedKeys {sData(), iData(), bData()};
    public ListQ() {
        root = null;
        length = 0;
    }
    public int getLength() {
        return length;
    }
    /**
     * Получение корневого элемента {@code root}
     */
    public LinkQ getLinkQRoot() {
        return root;
    }
    /**
     * Отображение связанного списка
     */
    public void displayList() {
        LinkQ temp = root;
        while(temp != null) {
            temp.displayNode();
            temp = temp.getNextChild();
        }
        System.out.println();
    }
    public void insert(int idt, String sdt, String bdt) {
        LinkQ newNode = new LinkQ(idt, sdt, bdt);
        if(root == null) {
            root = newNode;
            lastNode = newNode;
        }
        else {
            newNode.priorChild = lastNode;
            lastNode.setNextChild(newNode);
            lastNode = newNode;
        }
        length++;
    }
    /**
     * Добавление элемента в список со строковым ключом {@code key}
     * и целочисленным значением {@code idt} с возможностью инкремента {@code isInc} для значения {@code idt}
     */
    public boolean insertSData(String key, int idt, boolean isInc) {
        return insertSorted(idt, key, null, isInc, sortedKeys.sData);
    }
    /**
     * Добавление элемента в список со строковым ключом {@code code} и строковым значением {@code map}.
     */
    public boolean insertBData(String code, String map) {
        return insertSorted(1, map, code, true, sortedKeys.bData);
    }
    public boolean insertIData(int key, String value) {
        return insertSorted(key, value, null, false, sortedKeys.iData);
    }
    /**
     * Добавление элемента в список.
     * Три параметра: {@code idt}, {@code sdt}, {@code bdt} обрабатываются одновременно.
     * В зависимости от применения только один из этих параметров является ключевым (с сортировкой по ключу), остальные два используются для значений.
     * @param idt
     *        Целочисленные ключевые данные для поля iData элемента списка {@code LinkQ}.
     *        Не допускается дублирование значений.
     *        Используется для частотности символов кодируемого списка
     * @param sdt
     *        Строковые ключевые данные для поля sData элемента списка {@code LinkQ}.
     *        Не допускается дублирование значений.
     *        Используется для строковых ключевых строк (или строковой кодировки символов)
     * @param bdt
     *        Строковые ключевые данные для поля bData элемента списка {@code LinkQ}.
     *        Не допускается дублирование значений.
     *        Используется для строковой кодировки символов (или строковых ключевых строк)
     * @param isInc
     *        Признак использования икремента +1 значения в поле iData элемента списка {@code LinkQ}
     *        Используется только при добавлении целочисленных ключевых данных
     * @param key
     *        Вид сортировки элементов списка
     */
    public boolean insertSorted(int idt, String sdt, String bdt, boolean isInc, sortedKeys key) {
        LinkQ current = root;
        LinkQ parent = null;
        String sKey = null;
        Integer iKey = null;
        if(key == sortedKeys.sData) {
            sKey = sdt;
        }
        else if(key == sortedKeys.bData) {
            sKey = bdt;
        }
        else {
            iKey = idt;
        }
        if(sKey != null) {
            int result = 1;
            if(current != null) {
                result = current.getSData().compareTo(sKey);
            }
            while(current != null && result <= 0) {
                if(result == 0) {
                    if(isInc) {
                        current.incIData();
                        return true;
                    }
                    return false;
                }
                parent = current;
                current = current.getNextChild();
                if(current != null) {
                    result = current.getSData().compareTo(sKey);
                }
            }
        }
        else {
            while(current != null && current.getIData() <= iKey) {
                if(current.getIData() == iKey) {
                    return false;
                }
                parent = current;
                current = current.getNextChild();
            }
        }
        LinkQ newNode;
        if(key == sortedKeys.bData) {
            newNode = new LinkQ(idt, bdt, sdt); // sdt <-> bdt !!!
        }
        else {
            newNode = new LinkQ(idt, sdt, bdt);
        }
        if(root == null) {
            root = newNode;
            lastNode = newNode;
        }
        else if (parent == null) {
            newNode.setNextChild(root);
            root.priorChild = newNode;
            root = newNode;
        }
        else {
            newNode.priorChild = parent;
            parent.setNextChild(newNode);
            if(current == null) {
                lastNode = newNode;
            }
            else {
                newNode.setNextChild(current);
                current.priorChild = newNode;
            }
        }
        length++;
        return true;
    }
    /**
     * Находит элемент {@code LinkQ} по заданному строковому ключу {@code key}
     */
    public LinkQ find(String key) {
        LinkQ current = root;
        while(current != null && !current.getSData().equals(key)) {
            current = current.getNextChild();
            if(current == null) {
                return null;
            }
        }
        return current;
    }
    public boolean delete(String key) {
        LinkQ current = root;
        while(!current.getSData().equals(key)) {
            current = current.getNextChild();
            if(current == null) {
                return false;
            }
        }
        if(root == current) {
            root = current.getNextChild();
            current.priorChild = null;
            current.setNextChild(null);
        }
        else {
            if(current.getNextChild() != null) {
                current.getNextChild().priorChild = current.priorChild;
            }
            current.priorChild.setNextChild(current.getNextChild());
        }
        length--;
        return true;
    }
    public boolean deleteRoot() {
        if(root == null) {
            return false;
        }
        root = root.getNextChild();
        if(root == null) {
            lastNode = null;
        }
        length--;
        return true;
    }
}
