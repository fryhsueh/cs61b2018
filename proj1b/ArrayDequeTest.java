public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(0);
        ad.removeFirst();
        ad.addFirst(2);
        ad.removeLast();
        ad.addFirst(4);
        ad.addLast(5);
        ad.removeLast();
        ad.removeLast();
        ad.addFirst(8);
        ad.addFirst(9);
        ad.removeLast();
        ad.addFirst(11);
        ad.addFirst(12);
        ad.removeFirst();
        ad.addFirst(14);
        ad.addFirst(15);
        ad.addFirst(16);
        ad.removeFirst();
        ad.addFirst(18);
        ad.removeFirst();
        ad.removeFirst();
        ad.get(0);
        ad.removeFirst();
        ad.removeFirst();
    }
}
