import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LocalPlayer {

    private int width, height;
    private int x, y;
    private int[] pixel;
    private GameContainer gc;

    public LocalPlayer(String path, GameContainer gc) {

        this.gc = gc;
        x = 0;
        y = 0;
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
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        this.height = h;
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

    public void setX(int x) {
        this.x = (int)(x*gc.getScale());
        if(this.x < 0) this.x = 0;
        if((this.x+width)> gc.getGameWindow().getCanvas().getWidth()) this.x = gc.getGameWindow().getCanvas().getWidth()-width;
}

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = (int)(y*gc.getScale());
        if(this.y<0) this.y=0;
        System.out.println(this.y+height + " "+gc.getGameWindow().getCanvas().getHeight());
        if((this.y+height)> gc.getGameWindow().getCanvas().getHeight()) this.y = gc.getGameWindow().getCanvas().getHeight()-height;
    }
}
