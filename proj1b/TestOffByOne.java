import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.text.StyledEditorKit.BoldAction;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        boolean expect = true;
        boolean actual = offByOne.equalChars('a', 'b');
        assertEquals(expect, actual);

        assertFalse(offByOne.equalChars('a', 'a'));

    }



}
