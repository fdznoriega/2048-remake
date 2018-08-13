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
    private Integer[][] numbers = new Integer[4][4];
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
                numbers[i][j] = 0;
                labels[i][j] = new Label(numbers[i][j].toString());
            }
        }
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j]++;
                labels[i][j].setText(numbers[i][j].toString());
            }
        }
    }
    public void gameOver() {
      GameOver over = new GameOver();
      over.start(window);
    }
    public static void main(String args[]) {
        launch(args);
    }

}
