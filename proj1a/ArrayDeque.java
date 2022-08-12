import org.w3c.dom.ranges.Range;

public class ArrayDeque<T> {
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

    }

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
             *       ^^                        ^           ^              ^^                           ^       ^  
             *       nL/nF                     nL          nF             nL/nF                        nL      nF
             */

            /* copy left part */
            int numOfLeftPart = nextLast;
            System.arraycopy(items, 0, doubleSize, 0, numOfLeftPart);

            /* copy right part */
            int numOfRightPart = items.length - numOfLeftPart;
            int oldFirstIndex = numOfLeftPart;
            int newFirstIndex = doubleSize.length - numOfRightPart;
            System.arraycopy(items, oldFirstIndex, doubleSize, newFirstIndex, numOfRightPart);

            /* update index */
            nextFirst = indexMinusOne(newFirstIndex);
            items = doubleSize;
            return;
        }
        return;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
       
        System.out.println();

    }

    public T removeFirst() {
        return null;
    }

    public T removeLast() {
        return null;
    }
    
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
    }

}
