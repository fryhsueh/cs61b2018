public class SLList {
    private IntNode first;

    public SLList(int num) {
        first = new IntNode(num, null);
    }
    
    // add num in the front of the SLList
    public void addFirst(int num) {
        first = new IntNode(num, first);
    }

    // get 
    public int getFirst() {
        return first.item;
    }

    public void addLast(int num) {
        IntNode tail = first;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = new IntNode(num, null);
    }


    /**return the size of list the starts at IntNode n*/
    private static int size(IntNode n) {
        if (n.next == null) {
            return 1;
        }
        return 1 + size(n.next);
    } 

    public int size() {
        return size(first);
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
