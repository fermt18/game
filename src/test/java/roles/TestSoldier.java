package roles;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestSoldier {

    @Test void soldier_starting_data(){
        Soldier soldier = new Soldier(Status.A, 100, new int[]{0,0});
        assertThat(soldier.getStatus(), is(Status.A));
        assertThat(soldier.getHitPoints(), is(100));
        assertArrayEquals(new int[]{0,0}, soldier.getCoordinates());
    }

    @Test void soldier_is_hit(){
        Soldier soldier = new Soldier(Status.B, 100, new int[]{0,0});
        soldier.isHit(25);
        assertThat(soldier.getHitPoints(), is(75));
    }

    @Test void soldier_hits(){
        Soldier soldierA = new Soldier(Status.A, 100, new int[]{0,0});
        Soldier soldierB = new Soldier(Status.B, 100, new int[]{0,0});
        soldierA.hits(soldierB, 25);
        assertThat(soldierA.getHitPoints(), is(100));
        assertThat(soldierB.getHitPoints(), is(75));
    }

    @Test void soldier_moves(){
        Soldier soldier = new Soldier(Status.A, 100, new int[]{0,0});
        soldier.moves(10, 2);
        soldier.moves(-2, 1);
        assertArrayEquals(new int[]{3, 8}, soldier.getCoordinates());
    }

    @Test void soldier_finds_enemy(){
        Soldier soldierA = new Soldier(Status.A, 100, new int[]{1,0});
        Soldier soldierB = new Soldier(Status.B, 100, new int[]{0,0});
        assertThat(soldierA.isEnemyOnSight(soldierB.getCoordinates()), is(true));
    }

    @Test void soldier_does_not_find_enemy(){
        Soldier soldierA = new Soldier(Status.A, 100, new int[]{1,0});
        Soldier soldierB = new Soldier(Status.B, 100, new int[]{4,0});
        assertThat(soldierA.isEnemyOnSight(soldierB.getCoordinates()), is(false));
    }
}
