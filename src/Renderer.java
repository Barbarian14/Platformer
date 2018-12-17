import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Renderer {

    private int pW, pH;
    private int[] p;

    public Renderer(GameContainer gc) {
        pW = gc.getWidth();
        pH = gc.getHeight();
        p = ((DataBufferInt)gc.getGameWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {

        for(int i = 0; i<p.length; i++) {
            p[i] = 0xff000000;
        }

    }

    public void setPixel(int x, int y, int value) {
        if((x<0 || x>=pW || y<0 || y>= pH) || value == 0xffff00ff) {
            return;
        }
        p[x + y*pW] = value;
    }

    public void drawImage(LocalPlayer ply) {
        for(int x = 0; x < ply.getWidth(); x++) {
            for(int y = 0; y < ply.getHeight(); y++) {
                setPixel(x+ply.getX(), y+ply.getY(), ply.getPixel()[x+y*ply.getWidth()]);
            }
        }
    }


    public void drawObstacles(ArrayList<Obstacle> obstacles) {

        for(int i = 0; i<obstacles.size(); i++) {
            Obstacle obs = obstacles.get(i);
            for(int x = 0; x<obs.getWidth(); x++) {
                for(int y = 0; y<obs.getHeight(); y++) {
                    if(obs.getPixel() == null)
                        setPixel(x+obs.getX(), y+obs.getY(), obs.getColor());
                    else
                        setPixel(x+obs.getX(), y+obs.getY(), obs.getPixel()[x+y*obs.getWidth()]);
                }
            }

        }

    }
}
