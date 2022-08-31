package synthesizer;
import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        int[] items = { 1, 2, 3, 4, 5 };
        for (int item : items) {
            arb.enqueue(item);
        }

        for (int item : arb) {
            System.out.println(item);
        }

        for (int item : items) {
            int i = arb.dequeue();
            assertEquals(item, i);
        }

  
    }   


    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
