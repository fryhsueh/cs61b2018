public class LinkedListDeque<T> {
    /**centinel is set to avoid NullPointException problem, new item should always behind it */
    private QueueNode centinel;
    private int size;

    public LinkedListDeque() {
        centinel = new QueueNode(null);
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        
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
        first._next = centinel._next;
        first._prex = centinel;
        centinel._next._prex = first;
        centinel._next = first;
        increaseSize();
    }

    public void addLast(T item) {
        QueueNode last = new QueueNode(item);
        last._prex = centinel._prex;
        last._next = centinel;
        centinel._prex._next = last;
        centinel._prex = last;
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
        first._next._prex = centinel;
        centinel._next = first._next;

        decreaseSize();

        return first._item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        QueueNode last = centinel._prex;
        last._prex._next = centinel;
        centinel._prex = last._prex;

        decreaseSize();

        return last._item;
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
