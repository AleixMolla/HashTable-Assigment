public class Bucket {

    private String keyWord;
    private int count;

    /** Bucket Constructor */
    public Bucket (String key_word) {
        this.keyWord = key_word;
        this.count = 1;
    }

    /** Getters & Setters */
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String key_word) {
        this.keyWord = key_word;
    }

    public int getCount() {
        return count;
    }

    /** Increase & decrease the number of words */
    public void increaseCount() {
        count++;
    }

    public void decreaseCount() {
        count --;
    }


}
