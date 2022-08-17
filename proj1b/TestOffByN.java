import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        CharacterComparator cc = new OffByN(0);

        assertTrue(cc.equalChars('a', 'a'));

        cc = new OffByN(1);

        assertTrue(cc.equalChars('a', 'b'));
    }
}
