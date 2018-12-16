import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameWindow {
    
    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics graphics;

    private double fpsTimer;
    private int fps;

    public GameWindow(GameContainer gc) {

        image  = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);

        frame = new JFrame(gc.getGameName());
        canvas = new Canvas();
        Dimension resolution = new Dimension((int) (gc.getWidth()*gc.getScale()), (int) (gc.getHeight()*gc.getScale()));
        canvas.setPreferredSize(resolution);
        canvas.setMinimumSize(resolution);
        canvas.setMaximumSize(resolution);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        graphics = bs.getDrawGraphics();
        fpsTimer=System.nanoTime()/1000000000d;
    }

    public void update() {
            fps++;
        if(((System.nanoTime()/1000000000d)-fpsTimer) > 1d) {
            System.out.println("FPS : "+fps);
            fps=0;
            fpsTimer = System.nanoTime()/1000000000d;
        }

        graphics.drawImage(image, 0,0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();

    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getCanvas() {
        return canvas;
    }


}
