import java.util.Optional;
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

    private Stage window;
    private Label[][] labels = new Label[4][4];
    private int score;

    @Override
    public void start(Stage stage) {
        window = stage;
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

                grid.add(createSquare(i, j), i, j);
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
                    case UP:    updateLabels("up");     break;
                    case DOWN:  updateLabels("down");   break;
                    case LEFT:  updateLabels("left");   break;
                    case RIGHT: updateLabels("right");  break;
                  	case D: gameOver(); break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void initializeNumbers() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                labels[i][j] = new Label("0");
            }
        }
        insertRandom();
    }

    public StackPane createSquare(int i, int j) {
        StackPane stack = new StackPane();
        Label l = labels[i][j];
        l.setFont(new Font(30));
        Rectangle r = new Rectangle(80,80);
        r.setFill(Color.GREY);

        stack.getChildren().addAll(r, l);
        return stack;
    }

    public void updateLabels(String direction) {
        int freeSpaces = 16;


        //use labels[x][y]
        //Integer.parseInt(labels[x][y].getText());
        //run comparison before this method
        insertRandom();

    }

    public void gameOver() {
      GameOver over = new GameOver();
      over.start(window);
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
        spot = Integer.parseInt(labels[randX][randY].getText());
        if(spot == 0) {
          if((int) (99 * Math.random()) > 10) {
            labels[randX][randY].setText("2");
          } else {
            labels[randX][randY].setText("4");
          }
          randomInserted = true;
        }

      }
    }

    public static void main(String args[]) {
        launch(args);
    }

}
