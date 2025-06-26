package hashTable;

import listQ.LinkQ;
import listQ.ListQ;

public class HashTable {
    private final int arraySize;
    public ListQ[] array;
    public HashTable(int size) {
        arraySize = size;
        array = new ListQ[arraySize];
    }
    public int getArraySize() {
        return arraySize;
    }
    public ListQ[] getArray() {
        return array;
    }
    public int hashFunc(String key) {
        int hashValue = 0;
        for(int j = 0; j < key.length(); ++j) {
            int letter = key.charAt(j);
            hashValue = (hashValue * j + letter) % arraySize;
        }
        return hashValue;
    }
    public int inc(String key) {
        return put(key, 1, true);
    }
    public int put(String key, int value) {
        return put(key, value, false);
    }
//    public int put(String key, String value) {
//        return put(key, value, false);
//    }
    public int put(String key, int value, boolean isInc) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new ListQ();
        }
//        array[hashValue].insertSDataSorted(value, key, isInc);
        array[hashValue].insertSData(key);
        return value;
    }
    public String putCode(String key, String letter) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            array[hashValue] = new ListQ();
        }
//        array[hashValue].insertBCodeSorted(key, letter);
        array[hashValue].insertBData(key, letter);
        return key;
    }
    public int get(String key) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            return -1;
        }
        LinkQ link = array[hashValue].find(key);
        if (link == null) {
            return -1;
        }
//        return array[hashValue].find(key).getIData();
        return link.getIData();
    }
    public String getCode(String key) {
        int hashValue = hashFunc(key);
        if(array[hashValue] == null) {
            return null;
        }
        LinkQ current = array[hashValue].getLinkQRoot();
        while (current != null && !current.getSData().equals(key)) {
            current = current.getNextChild();
        }
        if (current != null) {
            return current.getBCode();
        }
        return null;
    }
    public void displayHash() {
        System.out.println("............................");
        for(int j = 0; j < array.length; ++j) {
            if(array[j] != null) {
                System.out.print(j + ": ");
                array[j].displayList();
            }
        }
    }

}
