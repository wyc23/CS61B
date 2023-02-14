public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque <Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }
    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (wordDeque.removeFirst() != wordDeque.removeLast()) {
            return false;
        }
        return isPalindromeHelper(wordDeque);
    }
    private boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cmp) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (!cmp.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return false;
        }
        return isPalindromeHelper(wordDeque, cmp);
    }
    public boolean isPalindrome(String word) {
        Deque <Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque <Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }
}
