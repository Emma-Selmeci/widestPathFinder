package eselmeci_collectionExtension;

import java.util.Vector;

/**
 * Vector<String> wrapper class that ensures ordering and implements binary search
 */

public class OrderedStringVector {

    private Vector<String> v;
    private String cachedString = "anystring";
    private int cachedIndex = -1;

    public OrderedStringVector() {
        v = new Vector<>();
    }

    public OrderedStringVector(int initialCapacity, int capacityIncrement) {
        v = new Vector<>(initialCapacity,capacityIncrement);
    }

    /**
     * Finds the index of name in v or returns -1 if name is not in vector
     * Uses result of getIndex()
     * @param name the index of name will be returned if found
     * @return the index of name in names or -1 if not found (not using an exception in this case as exceptions have bigger overhead)
     */
    public int find(String name) {
        if(name.equals(cachedString)) return cachedIndex;
        int index = getIndex(name);
        if(index > v.size()-1 || !v.elementAt(index).equals(name)) return -1; else return index;
    }

    /**
     * Add name to vector
     * @param name the name to be added, can be a name already in vector
     * @return true if name was element of the vector prior to calling add, false otherwise
     */
    public boolean add(String name) {
        cachedString = name;
        if(v.size() == 0) {
            cachedIndex = 0;
            v.add(name);
            return false;
        }
        int i = getIndex(name);
        if(i < v.size() && v.elementAt(i).equals(name)) {
            cachedIndex = i;
            return true;
        }
        cachedIndex = i;
        v.add(i,name);
        return false;
    }

    public void remove(int index) {
        cachedString = "anystring";
        cachedIndex = -1;
        v.remove(index);
    }

    /**
     * Finds the index of where name would be correctly ordered in the vector
     * Using the Bottenbruch algorithm
     * @param name the index of name will be returned if found
     * @return the index of name in names or -1 if not found (not using an exception in this case as exceptions have bigger overhead)
     */
    private int getIndex(String name) {
        if(v.size() == 0) return 0;
        int l = 0, r = v.size()-1;
        int m = (l+r+1)/2;
        while(l != r) {
            if(v.elementAt(m).compareTo(name) > 0) {
                r = m-1;
            } else {
                l = m;
            }
            m = (l+r+1)/2;
        }
        if(v.elementAt(m).compareTo(name) < 0) return m+1;
        return m;
    }

    //Test methods
    private void addTest(String name) {
        v.add(name);
    }
    private String getTest(int index) {return v.elementAt(index);}

    public static void main(String[] args) {

        OrderedStringVector vector = new OrderedStringVector();
        System.out.println("Testing OrderedStringVector");

        {
            System.out.println("\n###Testing getIndex()###");

            System.out.println("\nTesting empty vector");
            if (vector.getIndex("anystring") == 0) System.out.println("Test passed");
            else System.out.println("Test failed");

            System.out.println("\nTesting vector with one element");
            vector.addTest("b");
            if (vector.getIndex("a") == 0 && vector.getIndex("b") == 0 && vector.getIndex("c") == 1)
                System.out.println("Test passed");
            else System.out.println("Test failed");

            System.out.println("\nTesting vector with two elements");
            vector.addTest("d");
            if (vector.getIndex("a") == 0
                    && vector.getIndex("b") == 0
                    && vector.getIndex("c") == 1
                    && vector.getIndex("d") == 1
                    && vector.getIndex("e") == 2) System.out.println("Test passed");
            else System.out.println("Test failed");
        }
        {
            System.out.println("\n###Testing find()###");
            System.out.println("\nTesting empty vector");

            vector = new OrderedStringVector();

            if (vector.find("anystring") != -1) System.out.println("Test failed");
            else System.out.println("Test passed");

            System.out.println("\nTesting vector with one element");
            vector.addTest("a");
            if (vector.find("anystring") != -1 || vector.find("a") != 0) System.out.println("Test failed");
            else System.out.println("Test passed");

            System.out.println("\nTesting vector with two elements");
            vector.addTest("b");
            if (vector.find("anystring") != -1 || vector.find("a") != 0 || vector.find("b") != 1)
                System.out.println("Test failed");
            else System.out.println("Test passed");

            System.out.println("\nTesting vector with three elements");
            vector.addTest("c");
            if (vector.find("anystring") != -1
                    || vector.find("a") != 0
                    || vector.find("b") != 1
                    || vector.find("c") != 2) System.out.println("Test failed");
            else System.out.println("Test passed");

            System.out.println("\n Testing vector with many elements");
            vector.addTest("d");
            vector.addTest("e");
            vector.addTest("f");
            if (vector.find("anystring") != -1
                    || vector.find("a") != 0
                    || vector.find("b") != 1
                    || vector.find("c") != 2
                    || vector.find("d") != 3
                    || vector.find("e") != 4
                    || vector.find("f") != 5) System.out.println("Test failed");
            else System.out.println("Test passed");
        }

        System.out.println("\n###Testing add()###");
        System.out.println("\nAdding element to empty vector");

        vector = new OrderedStringVector();
        boolean result = vector.add("c");
        if(result == false && vector.find("c") == 0) System.out.println("Test passed"); else System.out.println("Test failed");

        System.out.println("\nAdding preexisting element");
        result = vector.add("c");
        if(result == true && vector.find("c") == 0) System.out.println("Test passed"); else System.out.println("Test failed");

        System.out.println("\nInserting element before");
        result = vector.add("a");
        if(result == false && vector.find("a") == 0 && vector.find("c") == 1) System.out.println("Test passed"); else System.out.println("Test failed");

        System.out.println("\nInserting element in the middle");
        result = vector.add("b");
        if(result == false && vector.find("a") == 0 && vector.find("b") == 1 && vector.find("c") == 2) System.out.println("Test passed"); else System.out.println("Test failed");

        System.out.println("\nInserting preexisting element again");
        result = vector.add("b");
        if(result == true && vector.find("a") == 0 && vector.find("b") == 1 && vector.find("c") == 2) System.out.println("Test passed"); else System.out.println("Test failed");


    }

}
