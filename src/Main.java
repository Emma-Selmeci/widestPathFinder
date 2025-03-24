public class Main {

    static void change(Boolean b) {
        b = false;
    }

    public static void main(String[] args) {
        Boolean b = true;
        change(b);
        System.out.println(b);
    }
}
