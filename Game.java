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
    private int score;

    @Override
    public void start(Stage stage) {

        score = 0;

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
                    case D: break;
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
        //insertRandom();
    }

    public void updateLabels(String direction) {
        switch (direction) {
            case "up":
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
        }
        insertRandom();
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

    public static void main(String args[]) {
        launch(args);
    }

}
