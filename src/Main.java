import java.util.Vector;

public class Main {

    static void change(Boolean b) {
        b = false;
    }

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        v.add(0);
        v.add(1);
        v.add(2);
        v.remove(1);
        System.out.println(v.toString());
    }
}
