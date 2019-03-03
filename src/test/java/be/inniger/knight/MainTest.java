package be.inniger.knight;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class MainTest {

    @Test
    void getNumbers() {
        List<Integer> numbers = new Main().getNumbers();

        assertEquals((Integer) 1, numbers.get(0));
        assertNotEquals((Integer) 1, numbers.get(1));
    }
}
