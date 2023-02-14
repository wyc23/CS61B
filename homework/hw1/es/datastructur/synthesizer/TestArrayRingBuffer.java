package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3,arb.fillCount());
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2,arb.fillCount());
        assertEquals(2, (int) arb.peek());
        assertEquals(2,arb.fillCount());
    }
}
