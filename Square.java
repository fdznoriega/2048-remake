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

        setNum(0);
        l.setFont(new Font(35));
        l.setTextFill(Color.rgb(20, 20, 20));

        stack.getChildren().addAll(r, l);
    }

    public boolean getChangeable() {
        return changeable;
    }

    public void setChangeable(boolean b) {
        changeable = b;
    }

    public int getNum() {
        return number;
    }

    public void setNum(int i) {
        number = i;
        l.setText(String.valueOf(number));
        setColor(i);
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

    public void setColor(int i) {
        switch(i) {
            case 0:      r.setFill(Color.rgb(160, 160, 160)); l.setTextFill(Color.rgb(20, 20, 20)); break;
            case 2:      r.setFill(Color.rgb(230, 230, 230)); l.setTextFill(Color.rgb(20, 20, 20)); break;
            case 4:      r.setFill(Color.rgb(220, 220, 220)); l.setTextFill(Color.rgb(20, 20, 20)); break;
            case 8:      r.setFill(Color.rgb(225, 204, 153)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 16:     r.setFill(Color.rgb(255, 178, 102)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 32:     r.setFill(Color.rgb(255, 155, 40));  l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 64:     r.setFill(Color.rgb(255, 90, 0));    l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 128:    r.setFill(Color.rgb(240, 240, 153)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 256:    r.setFill(Color.rgb(240, 240, 102)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 512:    r.setFill(Color.rgb(235, 235, 80));  l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 1024:   r.setFill(Color.rgb(250, 250, 65));  l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 2048:   r.setFill(Color.rgb(255, 255, 0));   l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 4096:   r.setFill(Color.rgb(255, 120, 120)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 8192:   r.setFill(Color.rgb(255, 70, 70));   l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 16384:  r.setFill(Color.rgb(255, 0, 0));     l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 32764:  r.setFill(Color.rgb(153, 204, 255)); l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 65536:  r.setFill(Color.rgb(51, 153, 255));  l.setTextFill(Color.rgb(255, 255, 255)); break;
            case 131072: r.setFill(Color.rgb(0, 90, 190));    l.setTextFill(Color.rgb(255, 255, 255));  break;
        }
    }
}
