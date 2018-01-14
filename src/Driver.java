import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static FrequencyHashTable EdgarAPoe = new FrequencyHashTable();
    public static FrequencyHashTable LoveCraft = new FrequencyHashTable();
    public static FrequencyHashTable MaryShelly = new FrequencyHashTable();

    public static ArrayList<Double> EAP;
    public static ArrayList<Double> HPL;
    public static ArrayList<Double> MWS;

    public static File f = new File ("train.csv");
    public static File test = new File ("test.csv");
    public static Scanner scan;

    public static int correct;

    public static void main (String[] args) {
        System.out.println("PreLoading...");
        try {
            WritersLoad();
            LoadTest();
        System.out.println("Loaded");
        System.out.println("A total of " + correct +"% are correct.");

        /** Test Remove method and Remove Common to try to increase precision */
        RemoveCommon();
        LoadTest();
        System.out.println("A total of " + correct +"% are correct.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveCommon() {
        String[] common =  {"and","the","a","I","it","one","in","my","to","he","she","of","me","for","by","her","this","die","sun","after"};
        for (String word : common) {
            EdgarAPoe.trueRemove(word);
            LoveCraft.trueRemove(word);
            MaryShelly.trueRemove(word);
        }
    }

    public static void LoadTest() throws IOException {
        scan = new Scanner(test);
        correct=0;
        while (scan.hasNext()) {
            // Reset ArrayLists
            EAP = new ArrayList<>();
            HPL = new ArrayList<>();
            MWS = new ArrayList<>();
            String rawLine = scan.nextLine();
            Record r = new Record(rawLine);
            for (String word : r.getText().split(" ")) {
                EAP.add(EdgarAPoe.getFrequency(word));
                HPL.add(LoveCraft.getFrequency(word));
                MWS.add(MaryShelly.getFrequency(word));
            }
            TestFrequency(r);
        }
    }

    /** Depending on Decimal zeros precision modifies the Correct answers */
    public static void TestFrequency(Record r) {
        double freqEAP=0.000000001, freqHPL=0.000000001, freqMWS=0.000000001;

        for ( double freq : EAP) {
            if (freq == 0)
                freq=0.0000001;
            freqEAP *= freq;
        }
        for ( double freq : HPL) {
            if (freq == 0)
                freq=0.0000001;
            freqHPL *= freq;
        }
        for ( double freq : MWS) {
            if (freq == 0)
                freq=0.0000001;
            freqMWS *= freq;
        }
        if ( freqEAP > freqHPL && freqEAP > freqMWS) {
            if (r.getAuthor().equals("EAP"))
                correct++;
        } else if ( freqHPL > freqMWS) {
            if (r.getAuthor().equals("HPL"))
                correct++;
        } else
            if (r.getAuthor().equals("MWS"))
                correct++;
    }

    /** Loads Up all FrequencyHashTables from train.csv file */
    public static void WritersLoad() throws IOException {
        scan = new Scanner(f);

        while (scan.hasNext()) {
            String rawLine = scan.nextLine();
            Record r = new Record(rawLine);
            if (r.getAuthor().equals("EAP")) {
                for (String word : r.getText().split(" ")) {
                    EdgarAPoe.increment(word);
                }
            } else if (r.getAuthor().equals("HPL")) {
                for (String word : r.getText().split(" ")) {
                    LoveCraft.increment(word);
                }
            } else if (r.getAuthor().equals("MWS")) {
                for (String word : r.getText().split(" ")) {
                    MaryShelly.increment(word);
                }
            }
        }
    }
}
