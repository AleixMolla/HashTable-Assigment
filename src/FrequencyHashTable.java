import java.util.ArrayList;
import java.util.Map;

public class FrequencyHashTable {

    private final int HASH_SIZE = 9973;

    private MapEntryList[] hashTable;

    /** HashTable Constructor */
    public FrequencyHashTable () {
        hashTable = new MapEntryList[HASH_SIZE];
    }

    public MapEntryList[] getHashTable() {
        return hashTable;
    }

    /** Add a new Bucket to the List on the hashTable */
    public void increment (String word) {
        int hashNum = CodeNum(word);
        // Create new ArrayList if is empty
        if (hashTable[hashNum] == null) {
            MapEntryList newList = new MapEntryList(word);
            hashTable[hashNum] = newList;
        } // If the bucket is not empty, compare word
        else {
            boolean exist = false;
            for (Bucket b : hashTable[hashNum].getList()) {
                if (b.getKeyWord().equals(word)) {
                    b.increaseCount();
                    exist = true;
                }
            }
            if (!exist) {
                hashTable[hashNum].getList().add(new Bucket(word));
            }
        }
    }

    /** Decrease the count, do not remove the entire word ( TO FIX )*/
    public void remove (String word) {
        int hashNum = CodeNum(word);

        if (hashTable[hashNum].isEmpty()) {
            System.out.println("Word "+word+" does not exist in the HashTable");
        } else {
            for (int i=0; i<hashTable[hashNum].getListSize(); i++) {
                if (hashTable[hashNum].getBucketCount(i).equals(word)) {
                    if (hashTable[hashNum].getBucketCount(i).getCount() == 0) {
                        hashTable[hashNum].getList().remove(i);
                        break;
                    } else {
                        hashTable[hashNum].getBucketCount(i).decreaseCount();
                        break;
                    }
                }
            }
        }
    }

    public void trueRemove (String word) {
        int hashNum = CodeNum(word);

        if (hashTable[hashNum].isEmpty()) {
            System.out.println("Word "+word+" does not exist in the HashTable");
        } else {
            for (int i=0; i<hashTable[hashNum].getListSize(); i++) {
                if (hashTable[hashNum].getBucketCount(i).equals(word)) {
                    hashTable[hashNum].getList().remove(i);
                    break;
                }
            }
        }
    }

    /** Returns Int hashed positive value from a String word */
    public int CodeNum(String word) {
        int hashNum = word.hashCode();

        if (hashNum<0) {
            hashNum = (hashNum*(-1))%HASH_SIZE;
        } else {
            hashNum = hashNum % HASH_SIZE;
        }
        return hashNum;
    }

    public double getFrequency(String word) {
        int hashNum = CodeNum(word);
        if (hashTable[hashNum] == null)
            return 0.0000001;
        for (int i=0; i<hashTable[hashNum].getListSize(); i++) {
            if (hashTable[CodeNum(word)].getBucketCount(i).getKeyWord().equals(word)) {
                return (hashTable[hashNum].getBucketCount(i).getCount())/ (double)totalCount();
            }
        }
        return 0;
    }

    public int totalCount() {
        int total=0;
        for (int i=0; i<HASH_SIZE; i++) {
            if (hashTable[i] != null) {
                for (Bucket b : hashTable[i].getList()) {
                    total += b.getCount();
                }
            }
        }
        return total;
    }
}
