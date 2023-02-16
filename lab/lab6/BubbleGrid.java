public class BubbleGrid {
    private int[][] bubbles;
    private UnionFind bubbleDSets;
    private int m, n;
    private int celling;
    
    public BubbleGrid(int[][] grid) {
        m = grid[0].length;
        n = grid.length;
        celling = n * m;
        bubbles = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bubbles[i][j] = grid[i][j];
            }
        }
        bubbleDSets = new UnionFind(n * m + 1);
    }

    private int rcTo1D(int r, int c) {
        if (r < 0) {
            return celling;
        }
        if (!boundaryCheck(r, c)) {
            throw new RuntimeException("out of boundary");
        }
        return r * m + c;
    }
    private int get(int r, int c) {
         if (r < 0) {
            return 1;
        }
        if (boundaryCheck(r, c)) {
            return bubbles[r][c];
        }
        return -1;
    }
    private int[][] neighbour(int r, int c) {
        int [][] pos = {{r - 1, c},
                        {r, c - 1},
                        {r + 1, c},
                        {r, c + 1}};
        return pos;

    }
    private boolean boundaryCheck(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
    private UnionFind toDS(int[][] grid) {
        UnionFind ds = new UnionFind(n * m + 1);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (get(r, c) != 1) {
                    continue;
                }
                for (int[] rc : neighbour(r, c)) {
                    int neighbourRow = rc[0];
                    int neighbourColumn = rc[1];
                    if (get(neighbourRow, neighbourColumn) != 1) {
                        continue;
                    }
                    ds.union(rcTo1D(r, c), rcTo1D(neighbourRow, neighbourColumn));
                }
            }
        }
        return ds;
    }
    private int fallingCount(UnionFind ds, int r, int c) {
        int count = 0;
        int [] reachedRoot = new int[] {-1, -1, -1, -1};
        int index = 0;
        for (int[] rc : neighbour(r, c)) {
            int neighbourR = rc[0];
            int neighbourC = rc[1];
            if (get(neighbourR, neighbourC) != 1) {
                index++;
                continue;
            }
            reachedRoot[index] = ds.find(rcTo1D(neighbourR, neighbourC));
            index++;
        }
        for (int i = 0; i < 4; i++) {
            if (reachedRoot[i] == celling || reachedRoot[i] == -1) {
                continue;
            }
            for (int j = i + 1; j < 4; j++) {
                if (reachedRoot[i] == reachedRoot[j]) {
                    reachedRoot[j] = -1;
                }
            }
            count += ds.sizeOf(reachedRoot[i]);
        }
        return count;
    }

    public int[] popBubbles(int[][] darts) {
        int[] fallNum = new int[darts.length];
        int r, c;
        int index = 0;
        for (int[] rc : darts) {
            r = rc[0];
            c = rc[1];
            bubbles[r][c] = 0;
            bubbleDSets = toDS(bubbles);
            fallNum[index] = fallingCount(bubbleDSets, r, c);
            index++;
        }
        return fallNum;
    }
}
