import java.awt.*;

public class Hitbox extends Rectangle {

    private int x, y, width, height;

    public Hitbox(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void updateBounds() {
        setBounds(this.x, this.y, this.width, this.height);
    }

    public void setX(int x) {
        this.x = x;
        updateBounds();
    }


    public void setY(int y) {
        this.y = y;
        updateBounds();
    }


    public void setWidth(int width) {
        this.width = width;
        updateBounds();
    }

    public void setHeight(int height) {
        this.height = height;
        updateBounds();
    }
}
