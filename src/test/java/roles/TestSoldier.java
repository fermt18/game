package roles;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestSoldier {

    @Test void soldier_starting_data(){
        Soldier soldier = new Soldier("A", 100, new int[]{0,0});
        assertThat(soldier.getTeam(), is("A"));
        assertThat(soldier.getHitPoints(), is(100));
        assertArrayEquals(new int[]{0,0}, soldier.getCoordinates());
    }

    @Test void soldier_is_hit(){
        Soldier soldier = new Soldier("B", 100, new int[]{0,0});
        soldier.isHit(25);
        assertThat(soldier.getHitPoints(), is(75));
    }

    @Test void soldier_hits(){
        Soldier soldierA = new Soldier("A", 100, new int[]{0,0});
        Soldier soldierB = new Soldier("B", 100, new int[]{0,0});
        soldierA.hits(soldierB, 25);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(75));
    }

    @Test void soldier_moves(){
        Soldier soldier = new Soldier("A", 100, new int[]{0,0});
        soldier.moves(10, 2);
        soldier.moves(-2, 1);
        assertArrayEquals(new int[]{8, 3}, soldier.getCoordinates());
    }
}
