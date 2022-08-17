public class ArrayDeque<T> implements Deque<T>{
    private int size;
    /**items is a circular array */
    /* nL: nextLast, nF: nextFirst
     *   0   1    2   3   4   5   
     * |_1_|_10_|_x_|_x_|_2_|_4_|
     *            ^   ^
     *            nL  nF
    */
    private T[] items;

    /**index of addFirst */
    private int nextFirst;

    /**index of addLast */
    private int nextLast;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        /*nextFirst can be set arbitrarily*/
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    @Override
    public void addFirst(T item) {

        items[nextFirst] = item;
        size += 1;
        nextFirst = indexMinusOne(nextFirst);
        
        resizeIfFulled();
    }
    
    private int indexMinusOne(int index) {
        int mod = items.length;
        int update = index - 1;
        return update >= 0 ? update : update + mod;
    }

    private int indexAddOne(int index) {
        int mod = items.length;
        int update = index + 1;
        return update % mod;
    }

    @Override
    public void addLast(T item) {
        //resiezeIfFulled()
        items[nextLast] = item;
        size += 1;

        // update index
        nextLast = indexAddOne(nextLast);
       
        resizeIfFulled();

    }
    
    private void resizeIfFulled() {
        if (size == items.length) {
            T[] doubleSize = (T[]) new Object[size * 2];
            /*
             *   0   1   2                 0   1   2   3   4   5      0   1   2                0   1   2   3   4   5
             * |_1_|_x_|_1_| =addFirst=> |_1_|_x_|_x_|_x_|_1_|_1_|  |_1_|_x_|_1_| =addLast=> |_1_|_1_|_x_|_x_|_x_|_1_|
             *       ^^                        ^       ^                  ^^                           ^       ^  
             *       nL/nF                     nL      nF                 nL/nF                        nL      nF
             */
            resizeItems(doubleSize);
        }
        return;
    }

    private void resizeItems(T[] array) {

        /* copy left part */
        int numOfLeftPart = nextLast;
        System.arraycopy(items, 0, array, 0, numOfLeftPart);

        /* copy right part */
        int numOfRightPart = size - numOfLeftPart;
        int oldFirstIndex = indexAddOne(nextFirst);
        int newFirstIndex = array.length - numOfRightPart;
        System.arraycopy(items, oldFirstIndex, array, newFirstIndex, numOfRightPart);

        /* update index */
        nextFirst = indexMinusOne(newFirstIndex);
        items = array;


    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
       
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int firstIndex = indexAddOne(nextFirst);
        T item = items[firstIndex];
        items[firstIndex] = null;
        size -= 1;
        nextFirst = firstIndex;

        resizeIfTooEmpty();
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int lastIndex = indexMinusOne(nextLast);
        T item = items[lastIndex];
        items[lastIndex] = null;
        size -= 1;
        nextLast = lastIndex;

        resizeIfTooEmpty();
        return item;
    }

    private void resizeIfTooEmpty() {
        if (size * 1.0 / items.length <= 0.25) {
            T[] halfSize = (T[]) new Object[items.length / 2];  
            /* 
             *    0   1   2   3   4   5                    0   1   2   3   4   5        0   1   2
             *  |_1_|_1_|_x_|_x_|_x_|_x_| =removeFirst=> |_x_|_1_|_x_|_x_|_x_|_x_| => |_1_|_x_|_x_|
             * 
             *            ^           ^                    ^       ^                        ^   ^
             *            nL          nF                   nF      nL                       nL  nF
             * 
             *    0   1   2   3   4   5                   0   1   2   3   4   5        0   1   2 
             *  |_1_|_1_|_x_|_x_|_x_|_x_| =removeLast=> |_1_|_x_|_x_|_x_|_x_|_x_| => |_1_|_x_|_x_|
             *            ^           ^                       ^               ^            ^   ^
             *            nL          nF                      nL              nF           nL  nF
             * 
             */
            if (items[0] != null) {
                resizeItems(halfSize);
            }
            else 
            {
                int oldFirstIndex = indexAddOne(nextFirst);
                System.arraycopy(items, oldFirstIndex, halfSize, 0, size);

                /*update index */
                items = halfSize;
                nextFirst = indexMinusOne(0);
                nextLast = size;
            }

        }
        return;

    }
    
    @Override
    public T get(int i) {
        int firstIndex = indexAddOne(nextFirst);
        int index = (firstIndex + i) % items.length;
        return items[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int[] list = { 1, 2, 3, 4, 5, 6, 7, 8, 10, 100, 1000};
        for (int item : list) {
            ad.addFirst(item);
            ad.addLast(item);
           
        }
        ad.printDeque();

        for (int item : list) {
            //ad.removeFirst();
            ad.removeLast();
        }
        ad.printDeque();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.printDeque();
    }

}
