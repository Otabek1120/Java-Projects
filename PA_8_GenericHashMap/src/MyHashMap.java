import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap {

    private Integer size;
    private Set<String> keySet;
    private Set<Integer> valSet;
    private ArrayList<HashNode> myHashTable;
    private final static int NUMBUCKETS = 8;
    

    public MyHashMap() {
        this.size = 0;
        keySet = new HashSet<String>();
        valSet = new HashSet<Integer>();
        myHashTable = new ArrayList<HashNode>();
        buildArray();
    }


    private int hash(String key) {
        int hashCode = key.hashCode();
        int index = hashCode % NUMBUCKETS;
        return Math.abs(index);
    }

    public void buildArray() {
        for (int i = 0; i < NUMBUCKETS; i ++) {
            myHashTable.add(i, null);
        }
    }

    /* 
     *
     */
    public void clear() {
        for (HashNode list : myHashTable) {
            list = null;
        }
        size = 0;
        keySet.clear(); valSet.clear();
    }


    /* 
     *
     */
    public boolean containsKey(String key) {
        return keySet.contains(key);
    }


    /* 
     *
     */
    public boolean containsValue(Integer val) {
        return valSet.contains(val);
    }


    /* 
     *
     */
    public int get(String key) {
        if (keySet.contains(key)) {
            int index = hash(key);
            HashNode node = myHashTable.get(index);
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
            
        }
        return 0; //ruturn null
        
    }


    /* 
     *
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /* 
     *
     */
    public Set<String> keySet() {
        return keySet;
    }


    /* 
     * 
     */
    public int put(String key, Integer val) {
        int retVal = 0;
        int index = hash(key);
        HashNode<String,Integer> newNode = new HashNode(key, val);
        if (keySet.contains(key)) {
            HashNode currNode = myHashTable.get(index);
            while (currNode != null) {
                if (currNode.key.equals(key)) {
                    retVal = currNode.value;
                    currNode.setValue(val);
                } 
                currNode = currNode.next;
            }   
        }
        
        
        System.out.println(myHashTable.size());
        myHashTable.get(index).add(pair);
        keySet.add(key);
        valSet.add(val);
        size ++;
        return 0; // returns null
    }


    /* 
     * Removes the mapping for the specified key from this map if present.
     * @param key - whose mapping is to be removed from the map
     * @return    - the previous value associated with key, 
     *              or null if there was no mapping for key. 
     */
    public int remove(String key) {
        if (keySet.contains(key)) {
            int index = hash(key);
            int valRemoved = myHashTable.get(index).get(0).val;
            myHashTable.get(index).clear();
            size --;
            return valRemoved;
        }
        return 0; // returns null
    }


    /* 
     * 
     */
    public int size() {
        return size;
    }


    /* 
     * 
     */
    public void printTable() {
        int numConflicts = 0;
        for (int i = 0; i < NUMBUCKETS; i ++) {
            LinkedList<Tuple> listOfPairs = myHashTable.get(i);
            ArrayList<String> keyList = new ArrayList<>();
            for (Tuple pair : listOfPairs) {
                keyList.add(pair.key);
            }
            int numConflictInLine = 0;
            if (listOfPairs.size() > 1) {
                numConflictInLine = listOfPairs.size() - 1;
            }
            System.out.println("Index " + i + ": (" + numConflictInLine + 
                                    " conflicts)," + keyList);
            numConflicts += numConflictInLine;
        }
        System.out.println("Total # of conflicts: " + numConflicts);
    }






    private class HashNode<String, Integer> {
        private String key;
        private int value;
    
        private HashNode<String, Integer> next;
    
        public HashNode(String key, int value)
        {
            this.setKey(key);
            this.setValue(value);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public HashNode<String, Integer> getNext() {
            return next;
        }

        public void setNext(HashNode<String, Integer> next) {
            this.next = next;
        }
    }
}


