import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstacle{
    private int x,y,height,width, color;
    private Hitbox hitbox;
    private int[] pixel = null;


    public Obstacle(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.color = 0xffffffff;
        hitbox = new Hitbox(x,y,width,height);
    }

    public Obstacle(String path, int x, int y) {
        this.x = x;
        this.y = y;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = image.getWidth();
        height = image.getHeight();
        pixel = image.getRGB(0,0,width,height,null,0,width);

        image.flush();
        hitbox = new Hitbox(x,y,width,height);
    }

    public int[] getPixel() {
        return pixel;
    }

    public void setPixel(int[] pixel) {
        this.pixel = pixel;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updateHitbox();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updateHitbox();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateHitbox();
    }

    public int getWidth() {
        return width;
    }

    public void updateHitbox() {
        hitbox.setX(this.x);
        hitbox.setY(this.y);
        hitbox.setHeight(this.height);
        hitbox.setWidth(this.width);
    }

    public void setWidth(int width) {
        this.width = width;
        updateHitbox();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}
