import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;


public class Game extends Application {

    private Square[][] squareArray = new Square[4][4];
    private Integer[][] copyArray = new Integer[4][4];
    private int score;
    private Stage window;

    @Override
    public void start(Stage stage) {
        window = stage;
        score = 0;

        initializeCopyArray();
        initializeNumbers();

        VBox vbox = new VBox();

        Label name = new Label("2048!");
        name.setFont(new Font(50));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.add(squareArray[i][j].getStackPane(), i, j);
            }
        }

        Label points = new Label("Points: " + score);
        points.setFont(new Font(30));

        vbox.getChildren().addAll(name, grid, points);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        Scene scene = new Scene(vbox, 480, 600);
        stage.setTitle("2048!");

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    updateLabels("up");    break;
                    case DOWN:  updateLabels("down");  break;
                    case LEFT:  updateLabels("left");  break;
                    case RIGHT: updateLabels("right"); break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void initializeNumbers() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squareArray[i][j] = new Square();
            }
        }
        insertRandom();
    }

    public void initializeCopyArray() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyArray[i][j] = 0;
            }
        }
    }

    public void updateLabels(String direction) {

        switch (direction) {
            case "up":
                addAndMoveUp();
                break;
            case "down":
                addAndMoveDown();
                break;
            case "left":
                addAndMoveLeft();
                break;
            case "right":
                addAndMoveRight();
                break;
        }
        setBacktoChangeable();
        if(checkForGameOver()) {
          gameOver();
        } else {
          if(!boardIsFull()) {
            if(gridChanged()) {
                insertRandom();
            }
          }
        }
        updateCopyArray();
    }

    public boolean gridChanged() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (copyArray[i][j] != squareArray[i][j].getNum()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateCopyArray() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyArray[i][j] = squareArray[i][j].getNum();
            }
        }
    }

    public void insertRandom() {
      boolean randomInserted = false;
      int randX, randY, spot;
      //While the random number has not been inserted, run.
      while(!randomInserted) {
        //Check random spot on the grid
        randX = (int) (4 * Math.random());
        randY = (int) (4 * Math.random());
        //If spot is free, place a 2 or 4 there
        spot = squareArray[randX][randY].getNum();
        if(spot == 0) {
          if((int) (99 * Math.random()) > 10) {
            squareArray[randX][randY].setNum(2);
          } else {
            squareArray[randX][randY].setNum(4);
          }
          randomInserted = true;
        }

      }
    }

    public void setBacktoChangeable() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squareArray[i][j].setChangeable(true);
            }
        }
    }

    public void addAndMoveUp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (squareArray[i][j].getNum() == squareArray[i][k].getNum() &&
                        squareArray[i][j].getChangeable() &&
                        squareArray[i][j].getNum() != 0) {

                        squareArray[i][j].setNum(squareArray[i][j].getNum() + squareArray[i][k].getNum());
                        squareArray[i][k].setNum(0);
                        squareArray[i][j].setChangeable(false);
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (squareArray[i][j].getNum() == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (squareArray[i][k].getNum() != 0) {
                            squareArray[i][j].setNum(squareArray[i][k].getNum());
                            squareArray[i][k].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void addAndMoveDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k > -1; k--) {
                    if (squareArray[i][j].getNum() == squareArray[i][k].getNum() &&
                        squareArray[i][j].getChangeable() &&
                        squareArray[i][j].getNum() != 0) {

                        squareArray[i][j].setNum(squareArray[i][j].getNum() + squareArray[i][k].getNum());
                        squareArray[i][k].setNum(0);
                        squareArray[i][j].setChangeable(false);
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (squareArray[i][j].getNum() == 0) {
                    for (int k = j - 1; k > -1; k--) {
                        if (squareArray[i][k].getNum() != 0) {
                            squareArray[i][j].setNum(squareArray[i][k].getNum());
                            squareArray[i][k].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void addAndMoveLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (squareArray[j][i].getNum() == squareArray[k][i].getNum() &&
                        squareArray[j][i].getChangeable() &&
                        squareArray[j][i].getNum() != 0) {

                        squareArray[j][i].setNum(squareArray[j][i].getNum() + squareArray[k][i].getNum());
                        squareArray[k][i].setNum(0);
                        squareArray[j][i].setChangeable(false);
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (squareArray[j][i].getNum() == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (squareArray[k][i].getNum() != 0) {
                            squareArray[j][i].setNum(squareArray[k][i].getNum());
                            squareArray[k][i].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void addAndMoveRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k > -1; k--) {
                    if (squareArray[j][i].getNum() == squareArray[k][i].getNum() &&
                        squareArray[j][i].getChangeable() &&
                        squareArray[j][i].getNum() != 0) {

                        squareArray[j][i].setNum(squareArray[j][i].getNum() + squareArray[k][i].getNum());
                        squareArray[k][i].setNum(0);
                        squareArray[j][i].setChangeable(false);
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (squareArray[j][i].getNum() == 0) {
                    for (int k = j - 1; k > -1; k--) {
                        if (squareArray[k][i].getNum() != 0) {
                            squareArray[j][i].setNum(squareArray[k][i].getNum());
                            squareArray[k][i].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    //returns true if no moves left
    public boolean noMoreMoves() {
      //check each direction. use plus method.
      //check up/down with 0, 1 and 0, 2

      for(int i = 0; i < 4; i++) {
        for(int j = 1; j < 3; j++) {
          //check above and below
          //if can add, return false
          if(squareArray[i][j].getNum() == squareArray[i][j - 1].getNum()
          || squareArray[i][j].getNum() == squareArray[i][j + 1].getNum()) {
            return false;
          }
        }
      }


      //check left/right with 1, 0 and 2, 0
      for(int i = 1; i < 3; i++) {
        for(int j = 0; j < 4; j++) {
          if(squareArray[i][j].getNum() == squareArray[i - 1][j].getNum()
          || squareArray[i][j].getNum() == squareArray[i + 1][j].getNum()) {
            return false;
          }
        }
      }

      //no more moves
      return true;
    }

    public boolean boardIsFull() {
      for(int i = 0; i < 4; i++) {
        for(int j = 0; j < 4; j++) {
          //if a number that isn't 0 is here, add 1 to spotsTaken
          if(squareArray[i][j].getNum() == 0) {
            return false;
          }
        }
      }
      return true;
    }
    //ends game and sends to game over menu
    public void gameOver() {
        GameOver over = new GameOver();
        over.start(window);
    }

    //returns true if the game is over
    public boolean checkForGameOver() {
      if(boardIsFull()) { return noMoreMoves(); }
      else              { return false;         }

    }

    public static void main(String args[]) {
        launch(args);
    }

}
