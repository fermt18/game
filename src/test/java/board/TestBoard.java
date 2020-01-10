package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roles.Soldier;
import roles.Status;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

class TestBoard {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @Test
    void soldiersListSize() {
        assertThat(board.getSoldierList().size(), is(2));
    }

    @Test
    void soldiersSpawnInDifferentPositions() {
        assertThat(board.getSoldierList().get(0).getCoordinates()[0],
                is(not(board.getSoldierList().get(1).getCoordinates()[0])));
        assertThat(board.getSoldierList().get(0).getCoordinates()[1],
                is(not(board.getSoldierList().get(1).getCoordinates()[1])));
    }

    @Test
    void soldiersAreSplitEquallyBetweenTeams() {
        assertThat(board.getSoldierList().get(0).getStatus(), is(Status.A));
        assertThat(board.getSoldierList().get(1).getStatus(), is(Status.B));
    }

    @Test
    void soldierPositionWithinBoard() {
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        assertThat(board.getBoardArray()[soldierA.getCoordinates()[0]][soldierA.getCoordinates()[1]],
                is("A"));
        assertThat(board.getBoardArray()[soldierB.getCoordinates()[0]][soldierB.getCoordinates()[1]],
                is("B"));
    }

    @Test
    void soldiers_shoot_each_other_when_on_sight() {
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(100));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(100));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(100));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(100));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(80));
        assertThat(soldierB.getHitPoints(), is(80));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(60));
        assertThat(soldierB.getHitPoints(), is(60));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(40));
        assertThat(soldierB.getHitPoints(), is(40));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(20));
        assertThat(soldierB.getHitPoints(), is(20));

        board.move(soldierA, 1, 1);
        board.move(soldierB, -1, -1);
        assertThat(soldierA.getHitPoints(), is(20));
        assertThat(soldierB.getHitPoints(), is(0));
    }

    @Test
    void a_dead_soldier_cannot_move_and_hit_or_be_hit() {
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        soldierA.hits(soldierB, 100);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(0));
        for (int i = 1; i < 9; i++) {
            board.move(soldierA, 1, 1);
            board.move(soldierB, -1, -1);
            assertThat(soldierA.getHitPoints(), is(100));
            assertThat(soldierB.getHitPoints(), is(0));
            assertThat(soldierA.getCoordinates(), is(new int[]{i, i}));
            assertThat(soldierB.getCoordinates(), is(new int[]{9, 9}));
        }
    }

    @Test
    void two_soldiers_cannot_stand_in_the_same_position() {
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        for (int i = 1; i < 9; i++) {
            board.move(soldierA, 1, 1);
            board.move(soldierB, -1, -1);
            if(i<4) {
                assertThat(soldierA.getCoordinates(), is(new int[]{i, i}));
                assertThat(soldierB.getCoordinates(), is(new int[]{9 - i, 9 - i}));
            }
            else{
                assertThat(soldierA.getCoordinates(), is(new int[]{4, 4}));
                assertThat(soldierB.getCoordinates(), is(new int[]{5, 5}));
            }
        }
    }
}
