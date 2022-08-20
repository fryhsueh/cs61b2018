import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Assert.*;
public class TestArrayDequeGold {
    @Test
    public void testArrayDequeGold() {
        /*Initialize */
        ArrayDequeSolution<Integer> as = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();

        Integer times = 500;
        StringBuilder message = new StringBuilder();

        /*Do 500 times random operation*/
        for (int i = 0; i < times; i += 1) {

            /*generate a random number to decide which method should be called*/
            int choice = StdRandom.uniform(0, 4);

            int num = StdRandom.uniform(times);

            switch (choice) {
                case 0:
                    addFirst(message, sd, as, num);
                    break;
                case 1:
                    addLast(message, sd, as, num);
                    break;
                case 2:
                    removeFirst(message, sd, as);
                    break;
                case 3:
                    removeLast(message, sd, as);
                    break;
            }

            int expectSize = as.size();
            int actualSize= sd.size();
            assertEquals(message.toString(), expectSize, actualSize);
        }
    }
    
    private void addFirst(StringBuilder message, StudentArrayDeque<Integer> sd, ArrayDequeSolution<Integer> as, Integer num) {
        message.append("addFirst(" + num + ")\n");
        sd.addFirst(num);
        as.addFirst(num);
    }

    private void addLast(StringBuilder message, StudentArrayDeque<Integer> sd, ArrayDequeSolution<Integer> as,
            Integer num) {
        message.append("addLast(" + num + ")\n");
        sd.addLast(num);
        as.addLast(num);
    }

    private void removeFirst(StringBuilder message, StudentArrayDeque<Integer> sd, ArrayDequeSolution<Integer> as) {
        if (as.isEmpty()) {
            return;
        }
        message.append("removeFirst()\n");
        Integer expected = as.removeFirst();
        Integer actual = sd.removeFirst();
        assertEquals(message.toString(), expected, actual);
    }

    private void removeLast(StringBuilder message, StudentArrayDeque<Integer> sd, ArrayDequeSolution<Integer> as) {
        if (as.isEmpty()) {
            return;
        }
        message.append("removeLast()\n");
        Integer expected = as.removeLast();
        Integer actual = sd.removeLast();
        assertEquals(message.toString(), expected, actual);
    }
}
