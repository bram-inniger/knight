package be.inniger.knight;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().getNumbers());
    }

    List<Integer> getNumbers() {
        return List.of(1, 2, 3, 4, 5);
    }
}
