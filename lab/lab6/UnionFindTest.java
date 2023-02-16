import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
// import org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void normalTest() {
        UnionFind myFind = new UnionFind(10);
        assertFalse(myFind.connected(0, 1));
        myFind.union(2, 3);
        myFind.union(5, 6);
        myFind.union(6, 7);
        assertTrue(myFind.connected(5, 7));
        assertFalse(myFind.connected(2, 7));
        myFind.union(3, 6);
        assertTrue(myFind.connected(2, 7));
    }
}
