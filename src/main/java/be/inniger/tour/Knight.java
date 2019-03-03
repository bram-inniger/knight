package be.inniger.tour;

import java.util.Set;

import static be.inniger.tour.Move.HorizontalOffset.left;
import static be.inniger.tour.Move.HorizontalOffset.right;
import static be.inniger.tour.Move.VerticalOffset.down;
import static be.inniger.tour.Move.VerticalOffset.up;

public class Knight {

    private static final Set<Move> MOVES = Set.of(
            Move.of(up(2), right(1)),
            Move.of(up(1), right(2)),
            Move.of(down(1), right(2)),
            Move.of(down(2), right(1)),
            Move.of(down(2), left(1)),
            Move.of(down(1), left(2)),
            Move.of(up(1), left(2)),
            Move.of(up(2), left(1)));
}
