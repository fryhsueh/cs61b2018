public class SLList {
    private IntNode sentinal;
    private int size = 0;

    public SLList() {
        sentinal = new IntNode(-1, null);
    }

    public SLList(int num) {
        this();
        sentinal.next = new IntNode(num, null);
        sizeIncrease();
    }
    
    // add num in the front of the SLList
    public void addFirst(int num) {
        sentinal.next = new IntNode(num, sentinal.next);
        sizeIncrease();
    }

    // get 
    public int getFirst() {
        return sentinal.next.item;
    }

    public void addLast(int num) {
        IntNode tail = sentinal.next;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = new IntNode(num, null);
        sizeIncrease();
    }

    private void sizeIncrease() {
        size += 1;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLList list = new SLList(10);
        System.out.println(list.size());
        list.addFirst(100);
        list.addFirst(1000);
        System.out.println(list.getFirst());
        list.addLast(2);
        System.out.println(list.size());

    }

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }

    }

}
