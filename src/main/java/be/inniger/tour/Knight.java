package be.inniger.tour;

import java.util.Set;

import static be.inniger.tour.Move.HorizontalOffset.left;
import static be.inniger.tour.Move.HorizontalOffset.right;
import static be.inniger.tour.Move.VerticalOffset.down;
import static be.inniger.tour.Move.VerticalOffset.up;
import static be.inniger.tour.Move.move;

public class Knight {

    private static final Set<Move> MOVES = Set.of(
            move(up(2), right(1)),
            move(up(1), right(2)),
            move(down(1), right(2)),
            move(down(2), right(1)),
            move(down(2), left(1)),
            move(down(1), left(2)),
            move(up(1), left(2)),
            move(up(2), left(1)));
}
