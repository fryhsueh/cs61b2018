public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        Deque<Character> deque = wordToDeque(word);

        return checkPalindromeInDeque(deque);
    }
    
    private boolean checkPalindromeInDeque(Deque<Character> deque) {

        int num = deque.size();

        if (num <= 1) {
            return true;
        }

        for (int i = 0; i < num / 2; i += 1) {
            Character first = deque.get(i);
            Character last = deque.get(num - i - 1);
            if (first != last) {
                return false;
            }
        }

        return true;
    }
    
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }

        return checkPalindroem(word, cc);
    }

    private boolean checkPalindroem(String word, CharacterComparator cc) {
        int num = word.length();

        if (num <= 1) {
            return true;
        }

        char first = word.charAt(0);
        char last = word.charAt(num - 1);

        if (!cc.equalChars(first, last)) {
            return false;
        }

        String rest = word.substring(1, num - 1);
        return checkPalindroem(rest, cc);
    }
}
