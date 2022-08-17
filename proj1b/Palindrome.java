public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            deque.addLast(c);
        }
        return deque;
    }


}
