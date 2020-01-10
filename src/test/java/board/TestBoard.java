package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roles.Soldier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBoard {

    private Board board;

    @BeforeEach void init(){
        board = new Board();
    }
    @Test void soldiersListSize(){
        assertThat(board.getSoldierList().size(), is(2));
    }

    @Test void soldiersSpawnInDifferentPositions(){
        assertThat(board.getSoldierList().get(0).getCoordinates()[0],
                is(not(board.getSoldierList().get(1).getCoordinates()[0])));
        assertThat(board.getSoldierList().get(0).getCoordinates()[1],
                is(not(board.getSoldierList().get(1).getCoordinates()[1])));
    }

    @Test void soldiersAreSplitEquallyBetweenTeams(){
        assertThat(board.getSoldierList().get(0).getTeam(), is("A"));
        assertThat(board.getSoldierList().get(1).getTeam(), is("B"));
    }

    @Test void soldierPositionWithinBoard(){
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        assertThat(board.getBoardArray()[soldierA.getCoordinates()[0]][soldierA.getCoordinates()[1]],
                is("A"));
        assertThat(board.getBoardArray()[soldierB.getCoordinates()[0]][soldierB.getCoordinates()[1]],
                is("B"));
    }

    @Test void moveSoldiers(){
        for(int i=0; i<10; i++) {
            printBoard(board.getBoardArray());
            board.move(board.getSoldierList().get(i%2==0 ? 0 : 1), 0, 1);
        }
    }

    private void printBoard(String[][] board){
        for(int x=0; x<10; x++){
            for(int y=0; y<10; y++){
                if(board[x][y] != null)
                    System.out.print(board[x][y]);
                else
                    System.out.print(".");
                System.out.print("  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
