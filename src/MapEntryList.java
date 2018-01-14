import java.util.ArrayList;

public class MapEntryList {

    private ArrayList<Bucket> list;

    /** MapEntryList Constructor */
    public MapEntryList(String word) {
        list = new ArrayList<Bucket>();
        list.add(new Bucket(word));
    }



    /** Getters and Setters */
    public ArrayList<Bucket> getList() {
        return list;
    }

    public void setList(ArrayList<Bucket> list) {
        this.list = list;
    }

    public int getListSize() {
        return list.size();
    }

    public Bucket getBucketCount(int i) {
        return list.get(i);
    }

    public boolean isEmpty() {
        if (list.isEmpty())
            return true;
        return false;
    }

}
