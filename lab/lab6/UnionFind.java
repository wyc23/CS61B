public class UnionFind {

    private int[] parentArray;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentArray = new int[n];
        for (int i = 0; i < parentArray.length; i++) {
            parentArray[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parentArray.length) {
            throw new RuntimeException("invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -1 * parentArray[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parentArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    /**
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int rootOfv1 = find(v1);
        int rootOfv2 = find(v2);
        if (rootOfv1 == rootOfv2) {
            return;
        }
        if (parentArray[rootOfv1] < parentArray[rootOfv2]) {
            parentArray[rootOfv1] += parentArray[rootOfv2];
            parentArray[rootOfv2] = rootOfv1;
            return;
        }
        parentArray[rootOfv2] += parentArray[rootOfv1];
        parentArray[rootOfv1] = rootOfv2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int r = vertex;
        while (parentArray[r] >= 0) {
            r = parentArray[r];
        }
        int pre = vertex;
        int next = parentArray[pre];
        while (next >= 0) {
            parentArray[pre] = r;
            pre = next;
            next = parentArray[pre];
        }
        return r;
    }

}
