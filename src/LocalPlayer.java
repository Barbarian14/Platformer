import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LocalPlayer {

    private int width, height;
    private int x, y;
    private int[] pixel;
    private GameContainer gc;
    private Hitbox hitbox;
    private int speed = 3;
    private double gravity;
    boolean jumping=false;

    public LocalPlayer(String path, GameContainer gc) {

        this.gc = gc;
        x = 0;
        y = 0;
        gravity = 0;
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

    public void updateHitbox(){
        hitbox.setY(this.y);
        hitbox.setX(this.x);
        hitbox.setHeight(this.height);
        hitbox.setWidth(this.width);
    }


    public double getGravity() {
        return gravity;
    }

    public void setGravity(double d) {
        this.gravity = d;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        this.width = w;
        updateHitbox();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        this.height = h;
        updateHitbox();
    }

    public int[] getPixel() {
        return pixel;
    }

    public void setPixel(int[] p) {
        this.pixel = p;
    }

    public int getX() {
        return x;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setX(int x) {

        if(this.x < x) {
            for(int i = this.x; i<=x; i++) {
                this.x++;
                if(gc.hasCollision()) {
                    this.x++;
                    i = x+1;
                }
            }
        } else if(this.x > x) {
            int k = this.x;
            for(int i = x; i<=k; i++) {
                this.x--;
                if(gc.hasCollision()) {
                    this.x--;
                    i = x+1;
                }
            }
        }

        if(this.x < 0) this.x = 0;
        if((this.x+width)> gc.getGameWindow().getCanvas().getWidth()) this.x = gc.getGameWindow().getCanvas().getWidth()-width;
        updateHitbox();
}

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(this.y < y) {
            for(int i = this.y; i<=y; i++) {
                this.y++;
                if(gc.hasCollision()) {
                    this.y++;
                    i = y+1;
                }
            }
        } else if(this.y > y) {
            int k = this.y;
            for(int i = y; i<=k; i++) {
                this.y--;
                if(gc.hasCollision()) {
                    this.y--;
                    i = y+1;
                }
            }
        }
        if(this.y<0) this.y=0;
        if((this.y+height)> gc.getGameWindow().getCanvas().getHeight()) this.y = gc.getGameWindow().getCanvas().getHeight()-height;
        updateHitbox();
    }
    public boolean getJumping(){
        return jumping;
    }
    public void setJumping(boolean jumping){
        this.jumping=jumping;
    }
}
