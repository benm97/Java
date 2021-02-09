import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class analyzes performances of the Simple Sets.
 */
public class SimpleSetPerformanceAnalyzer {

    private static String[] data1 = Ex3Utils.file2array("src/data1.txt");
    private static String[] data2 = Ex3Utils.file2array("src/data2.txt");

    //Sets we have to test
    private static SimpleSet openHash;
    private static SimpleSet closedHash;
    private static SimpleSet treeSet;
    private static SimpleSet hashSet;
    private static SimpleSet linkedList;

    //Names of all the data structures
    private static String[] structuresNames = {"Closed Hash Set", "Open Hash Set", "Tree Set", "Hash Set"
            , "Linked List"};

    //Variable that represents the number to convert ns to ms.
    private static final int NS_TO_MS = 1000000;

    //Variable that represents the number of iterations.
    private static final int WARMS_UP_TIME = 70000;


    /**
     * Initializes the data structures.
     */
    private static void init() {
        openHash = new OpenHashSet();
        closedHash = new ClosedHashSet();
        treeSet = new CollectionFacadeSet(new TreeSet<>());
        hashSet = new CollectionFacadeSet(new HashSet<>());
        linkedList = new CollectionFacadeSet(new LinkedList<>());
    }

    /**
     * Adds words from an array to the given data structure.
     *
     * @param struct the data structure
     * @param words  the array
     * @return the time it takes
     */
    private static long addWords(SimpleSet struct, String[] words) {
        long timeBefore1 = System.nanoTime();
        for (String value : words)
            struct.add(value);
        return (System.nanoTime() - timeBefore1) / NS_TO_MS;
    }

    /**
     * Checks if a word is in a given data structure.
     *
     * @param word   the word it checks
     * @param struct the data structure
     * @return the time it takes
     */
    private static long containsWord(String word, SimpleSet struct) {
        long timeBefore = System.nanoTime();
        for (int i = 0; i < WARMS_UP_TIME; i++) {
            struct.contains(word);
        }
        return (System.nanoTime() - timeBefore) / WARMS_UP_TIME;
    }

    /**
     * Runs the analyzer.
     *
     * @param args
     */
    public static void main(String[] args) {

        init();
        SimpleSet[] testArray1 = {closedHash, openHash, treeSet, hashSet, linkedList};
        int counter = 0;
        for (SimpleSet struct : testArray1) {
            System.out.println("time to insert data1 in " + structuresNames[counter] + " (ms): "
                    + addWords(struct, data1));
            System.out.println("time to search hi in " + structuresNames[counter] + " (ns): "
                    + containsWord("hi", struct));
            System.out.println("time to search -13170890158 in " + structuresNames[counter] + " (ns): "
                    + containsWord("-13170890158", struct));
            counter++;
        }
        init();
        SimpleSet[] testArray2 = {closedHash, openHash, treeSet, hashSet, linkedList};
        counter = 0;
        for (SimpleSet struct : testArray2) {
            System.out.println("time to insert data2 in " + structuresNames[counter] + " (ms): "
                    + addWords(struct, data2));
            System.out.println("time to search 23 in " + structuresNames[counter] + " (ns): "
                    + containsWord("23", struct));
            System.out.println("time to search hi in " + structuresNames[counter] + " (ns): "
                    + containsWord("hi", struct));
            counter++;
        }
    }

}
