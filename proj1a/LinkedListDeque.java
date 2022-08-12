public class LinkedListDeque<T> {
    /**centinel is set to avoid NullPointException problem, new item should always behind it */
    private QueueNode centinel;
    private int size;

    public LinkedListDeque() {
        centinel = new QueueNode(null);
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        /*weird syntax for pass autograder */
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }

    }

    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        return getItemAtIndex(index, centinel._next);
    }
    
    private T getItemAtIndex(int index, QueueNode node) {
        if (index == 0) {
            return node._item;
        }
        return getItemAtIndex(index - 1, node._next);
    }

    public void addFirst(T item) {
        QueueNode first = new QueueNode(item);
        QueueNode oldFirst = centinel._next;
        connect(centinel, first);
        connect(first, oldFirst);
        increaseSize();
    }

    public void addLast(T item) {
        QueueNode last = new QueueNode(item);
        QueueNode oldLast = centinel._prex;
        connect(oldLast, last);
        connect(last, centinel);
        increaseSize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }

        int left = size;
        QueueNode currNode = centinel._next;

        while (left > 1) {
            System.out.print(currNode._item + " ");
            left -= 1;
            currNode = currNode._next;
        }
        System.out.println(currNode._item);
    }
    
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        QueueNode first = centinel._next;
        QueueNode behindFirst = first._next;
        connect(centinel, behindFirst);
        decreaseSize();

        return first._item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        QueueNode last = centinel._prex;
        QueueNode aboveLast = last._prex;
        connect(aboveLast, centinel);
        decreaseSize();

        return last._item;
    }

    private void connect(QueueNode head, QueueNode tail) {
        head._next = tail;
        tail._prex = head;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        QueueNode currNode = centinel._next;
        while (index > 0) {
            currNode = currNode._next;
            index -= 1;
        }

        return currNode._item;
    }

    private void increaseSize() {
        size += 1;
    }

    private void decreaseSize() {
        size -= 1;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> ld = new LinkedListDeque<>();
        System.out.println(ld.isEmpty());
        ld.addFirst(1);
        ld.addFirst(2);
        ld.addFirst(10);
        ld.addFirst(100);
        System.out.println(ld.isEmpty());
        System.out.println(ld.get(2));
        System.out.println(ld.getRecursive(2));
        System.out.println(ld.size);
        ld.printDeque();
        ld.removeFirst();
        ld.printDeque();
        ld.removeLast();
        ld.printDeque();
    }

    private class QueueNode {
        private T _item;
        private QueueNode _prex;
        private QueueNode _next;
        
        public QueueNode(T item) {
            _item = item;
            _prex = this;
            _next = this;
        }
    }
}
