package board;

import roles.Soldier;
import roles.Status;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int BOARD_BOUNDARY = 10;
    private String[][] boardArray = new String[BOARD_BOUNDARY][BOARD_BOUNDARY];
    private List<Soldier> soldierList;

    public String[][] getBoardArray(){ return this.boardArray; }
    public List<Soldier> getSoldierList(){ return this.soldierList; }

    public Board(){
        soldierList = new ArrayList<>();
        soldierList.add(new Soldier(Status.A, 100, new int[]{0, 0}));
        soldierList.add(new Soldier(Status.B, 100, new int[]{9, 9}));
        updateBoard();
    }

    public void move(Soldier soldier, int newX, int newY){
        if(soldier.getHitPoints() > 0) {
            if (checkCoordinates(soldier.getCoordinates(), newX, newY)) {
                soldier.moves(newY, newX);
            }
            for (Soldier soldierFromList : soldierList) {
                if (!soldierFromList.equals(soldier) &&
                        soldier.isEnemyOnSight(soldierFromList.getCoordinates()) &&
                        soldierFromList.getHitPoints() > 0) {
                    soldier.hits(soldierFromList, 20);
                }
            }
        }
        updateBoard();
    }

    private void updateBoard(){
        boardArray = new String[BOARD_BOUNDARY][BOARD_BOUNDARY];
        for(Soldier soldier : soldierList){
            boardArray[soldier.getCoordinates()[0]][soldier.getCoordinates()[1]] = soldier.getStatus().toString();
        }
    }

    private boolean checkCoordinates(int[] coordinates, int newX, int newY){
        return (((coordinates[0] + newX < BOARD_BOUNDARY) && (coordinates[1] + newY < BOARD_BOUNDARY)) &&
                ((coordinates[0] + newX >= 0) && (coordinates[1] + newY >= 0)) &&
                (boardArray[coordinates[0]+newX][coordinates[1]+newY] == null));
    }
}
