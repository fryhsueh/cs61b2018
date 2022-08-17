import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String[] PALINDROME = { "a", "racecar", "" };

        for (String word : PALINDROME) {
            assertTrue(palindrome.isPalindrome(word));
        }

        String[] NOTPALINDROME = { "null", "cat" };

        for (String word : NOTPALINDROME) {
            assertFalse(palindrome.isPalindrome(word));
        }


        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("aabb", cc));

        assertTrue(palindrome.isPalindrome("bbaa", cc));

    }
}
