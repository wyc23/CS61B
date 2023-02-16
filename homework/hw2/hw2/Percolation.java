package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; // false for blocked, true for open
    private WeightedQuickUnionUF uf; // N * N is for the source, N * N + 1 is for the drain
    private final int N;
    private int numberOfOpenSites;
    private final int source, drain;
    private final int[][] neighbor = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    private int rcTo1D(int row, int col) {
        if (!boundaryCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return row * N + col;
    }

    private void connectNeighbors(int row, int col) {
        if (!boundaryCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (row == 0) {
            uf.union(source, rcTo1D(row, col));
        }
        if (row == N - 1) {
            uf.union(drain, rcTo1D(row, col));
        }
        int neighborRow, neighborCol;
        for (int[] rc : neighbor) {
            neighborRow = row + rc[0];
            neighborCol = col + rc[1];
            if (!boundaryCheck(neighborRow, neighborCol)) {
                continue;
            }
            if (!isOpen(neighborRow, neighborCol)) {
                continue;
            }
            uf.union(rcTo1D(neighborRow, neighborCol), rcTo1D(row, col));
        }
    }

    public Percolation(int N) {
        grid = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = false;
            }
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        numberOfOpenSites = 0;
        source = N * N;
        drain = N * N + 1;
    }
    public void open(int row, int col) {
        if (!boundaryCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        connectNeighbors(row, col);
        numberOfOpenSites++;
    }
    public boolean isOpen(int row, int col) {
        if (!boundaryCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        if (!boundaryCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return uf.connected(rcTo1D(row, col), source);
    }
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
    public boolean percolates() {
        return uf.connected(source, drain);
    }
}
