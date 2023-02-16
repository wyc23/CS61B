import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class BubbleGridTest {
    @Test
    public void test() {
        int[][] grid = {{1, 1, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 1, 1}};
        int[][] darts = {{2, 2},
                         {2, 0}};
        BubbleGrid bg = new BubbleGrid(grid);
        int[] expected = {0, 4};
        assertArrayEquals(expected, bg.popBubbles(darts));
    }
}
