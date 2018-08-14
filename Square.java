import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    private boolean changeable;
    private int number;
    private StackPane stack;
    private Label l;
    private Rectangle r;

    public Square() {
        changeable = true;

        stack = new StackPane();
        l = new Label("0");
        r = new Rectangle(80, 80);

        l.setFont(new Font(30));

        r.setFill(Color.GREY);

        stack.getChildren().addAll(r, l);
    }

    public boolean getChangable() {
        return changeable;
    }

    public void setChangable(boolean b) {
        changeable = b;
    }

    public int getNum() {
        return number;
    }

    public void setNum(int i) {
        number = i;
    }

    public StackPane getStackPane() {
        return stack;
    }

    public Label getLabel() {
        return l;
    }

    public void setLabel(Label l) {
        this.l = l;
    }
}
