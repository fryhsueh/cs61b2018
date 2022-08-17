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

}
