package app;

import board.Board;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import roles.Soldier;

import java.util.Random;

public class GameLoop extends AnimationTimer {

    private GraphicsContext gc;
    private Board board;
    private boolean turn;

    public GameLoop(GraphicsContext gc){
        this.gc = gc;
        this.board = new Board();
        turn = true;
    }

    @Override
    public void handle(long now) {
        gc.clearRect(0,0,400,200);
        //String[][] board = new String[10][10];
        //Random random = new Random();
        //board[random.nextInt(10)][random.nextInt(10)] = "A";
        Soldier soldierA = board.getSoldierList().get(0);
        Soldier soldierB = board.getSoldierList().get(1);
        board.move(turn ? soldierA : soldierB, 0, 1);
        turn = !turn;
        printBoard(board.getBoardArray());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){}
    }

    private void printBoard(String[][] board){
        Font pointFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        Font letterFont = Font.font("Times New Roman", FontWeight.BOLD, 10);
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