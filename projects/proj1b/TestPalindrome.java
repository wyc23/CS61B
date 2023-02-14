import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        CharacterComparator cmp = new OffByOne();
        CharacterComparator cmpByThree = new OffByN(3);
        assertTrue(palindrome.isPalindrome("tenet"));
        assertFalse(palindrome.isPalindrome("haha"));
        assertTrue(palindrome.isPalindrome("hahah"));
        assertTrue(palindrome.isPalindrome("abab", cmp));
        assertTrue(palindrome.isPalindrome("adad", cmpByThree));
    }
}