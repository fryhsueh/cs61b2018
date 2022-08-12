public class ArrayDeque<T> {
    private int size;
    /**items is a circular array 
    */
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
        /*Add item, then check whether is fulled rather than contrast*/
        if (size == items.length) {
            T[] doubleSize = (T[]) new Object[size * 2];
            /*if nF == 0 */
            if (nextFirst == 0) {
                /*
                 *    0   1   2                 0   1   2   3   4   5
                 *  |_x_|_1_|_1_| =addFirst=> |_1_|_1_|_1_|_x_|_x_|_x_|
                 *    ^^                                    ^       ^
                 *    nF/nL                                 nL      nF   
                 */
                System.arraycopy(items, 0, doubleSize, 0, size);
                nextFirst = doubleSize.length - 1;
                nextLast = nextLast + 1;
                items = doubleSize;
                return;
            }

            /*
             * items is fulled because of addFirst
             *   0   1   2        0   1   2   3   4   5
             * |_1_|_x_|_1_| => |_1_|_x_|_x_|_x_|_1_|_1_|
             *       ^^               ^       ^
             *       nF/nL            nL      nF
             *
             * At this time, nF == nL == num of items of left side which is 3
             */

            /*copy left part */
            int numOfLeftPart = nextLast;
            System.arraycopy(items, 0, doubleSize, 0, numOfLeftPart);

            /*copy right part */
            int numOfRightPart = items.length - numOfLeftPart;
            int oldFirstIndex = nextFirst;
            int newFirstIndex = doubleSize.length - numOfLeftPart;
            System.arraycopy(items, oldFirstIndex, doubleSize, newFirstIndex, numOfRightPart);

            /*update index */
            nextFirst = newFirstIndex - 1;
            return;
        }
        // resizeIfFulled();

        /*update index */
        nextFirst = indexMinusOne(nextFirst);

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

    private void resizeIfFulled() {
        if (size < items.length) {
            return;
        }

        T[] doubleSize = (T[]) new Object[size * 2];
        
        if (nextFirst < nextLast) {
            System.arraycopy(items, 0, doubleSize, 0, size);
            items = doubleSize;

        }
        
        if (nextFirst > nextLast) {
        
        }
    }

    public void addLast(T item) {
        //resiezeIfFulled()
        items[nextLast] = item;
        size += 1;

        if (size == items.length) {
            T[] doubleSize = (T[]) new Object[size * 2];
            /*if nL == end */
            if (nextLast == items.length - 1) {
                /*
                 * |_1_|_1_|_x_| => |_1_|_1_|_1_|_x_|_x_|_x_|
                 * 
                 */
                System.arraycopy(items, 0, doubleSize, 0, size);
                nextFirst = doubleSize.length - 1;
                nextLast = nextLast + 1;
                items = doubleSize;
                return;
            }
            /* items is fulled because of addLast
             *  
             * |_1_|_x_|_1_| => |_1_|_1_|_x_|_x_|_x_|_1_|
             *       ^^                   ^       ^
             *       nL/nF                nL      nF
             * 
             */
            /*copy left part */
            int numOfLeftPart = nextLast + 1;
            System.arraycopy(items, 0, doubleSize, 0, numOfLeftPart);
            /*copy right part */
            int numOfRightPart = items.length - numOfLeftPart;
            int oldFirstindex = numOfLeftPart;
            int newFirstIndex = doubleSize.length - numOfRightPart;
            System.arraycopy(items, oldFirstindex, doubleSize, newFirstIndex, numOfRightPart);

            //update index
            nextFirst = indexMinusOne(newFirstIndex);
            nextLast = indexAddOne(nextLast);
            items = doubleSize;
            return;
        }
        nextLast = indexAddOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public T removeFirst() {
        return null;
    }

    public T removeLast() {
        return null;
    }
    
    public T get(int index) {
        return null;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int[] list = { 1, 2, 3, 4, 5, 6, 7, 8, 10};
        for (int item : list) {
            ad.addLast(item);
        }
    }

}
