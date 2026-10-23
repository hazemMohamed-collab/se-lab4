package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void newStore_HasRequestedCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void newStore_WithZeroTorpedoes_IsEmpty() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fireOne_DecreasesCountByOne() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertTrue(store.fire(1));

        assertEquals(2, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireMultiple_DecreasesCountByRequestedAmount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertTrue(store.fire(3));

        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fireAll_LeavesStoreEmpty() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertTrue(store.fire(3));

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void firingFailure_PreservesCount() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        assertFalse(store.fire(1));

        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fireZero_ThrowsAndPreservesCount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(0));

        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireNegative_ThrowsAndPreservesCount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(-1));

        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireMoreThanAvailable_ThrowsAndPreservesCount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(4));

        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fireFromEmptyStore_ThrowsAndRemainsEmpty() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(1));

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }
}