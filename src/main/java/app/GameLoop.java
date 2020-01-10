package app;

import board.Board;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import roles.Soldier;

import java.util.List;

public class GameLoop extends AnimationTimer {

    private GraphicsContext gc;
    private Board board;
    private boolean turn;

    private Font letterFont = Font.font("Times New Roman", FontWeight.BOLD, 10);

    GameLoop(GraphicsContext gc){
        this.gc = gc;
        this.board = new Board();
        turn = true;
    }

    @Override
    public void handle(long now) {
        gc.clearRect(0,0,400,200);
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        if(turn){
            board.move(soldierA, 1, 1);
        }
        else {
            board.move(soldierB, -1, -1);
        }
        turn = !turn;
        printPoints(board.getSoldierList());
        printBoard(board.getBoardArray());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){}
    }

    private void printPoints(List<Soldier> soldierList){
        int height = 25;
        for(Soldier soldier : soldierList){
            gc.setFont(letterFont);
            gc.setFill(Color.BLACK);
            gc.fillText("Soldier " + soldier.getStatus().toString() + ": " + soldier.getHitPoints(), 150, height);
            height+=10;
        }
    }

    private void printBoard(String[][] board){
        Font pointFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                if(board[x][y] != null) {
                    gc.setFont(letterFont);
                    gc.setFill(Color.BLUE);
                    gc.fillText(board[x][y], x * 10, 10 + y * 10);
                }
                else {
                    gc.setFont(pointFont);
                    gc.setFill(Color.RED);
                    gc.fillText(".", x * 10, 10 + y * 10);
                }
            }
        }
    }
}
