import java.util.ArrayList;

public class GameContainer implements Runnable{

    private Thread thread;
    private boolean isRunning = false;
    private final double UPDATE_RATE = 1d/60d;

    private String gameName = "Platformer";

    private int width = 1600;
    private int height = 900;
    private float scale = 1f;
    private GameWindow gameWindow;
    private Renderer renderer;
    private LocalPlayer ply;
    private ArrayList<Obstacle> obstacles;
    private Input input;

    public static void main(String[] args) {
        GameContainer gc = new GameContainer();
    }

    public GameContainer() {
        start();
    }

    public void start(){
        ply = new LocalPlayer("player.png", this);
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(this, 500,500,100,50));
        obstacles.add(new Obstacle(this, 100, 500, 50, 50));
        gameWindow = new GameWindow(this);
        renderer = new Renderer(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {

        isRunning = true;
        double firstTime, lastTime;

        firstTime = System.nanoTime()/1000000000d;

        while(isRunning) {
            lastTime = System.nanoTime()/1000000000d;

            if((lastTime-firstTime) >= UPDATE_RATE) {

                if(ply.getX() >= 0 && ply.getY() >= 0 && ply.getWidth() <= gameWindow.getCanvas().getWidth() && ply.getHeight() <= gameWindow.getCanvas().getHeight()) {

                    if (input.isMovingDown()) ply.setY(ply.getY() + 5);
                    if (input.isMovingUp()) ply.setY(ply.getY() - 5);
                    if (input.isMovingLeft()) ply.setX(ply.getX() - 5);
                    if (input.isMovingRight()) ply.setX(ply.getX() + 5);

                    checkCollisions();
                }
                renderer.clear();
                renderer.drawImage(ply);
                renderer.drawObstacles(obstacles);

                gameWindow.update();
                firstTime = System.nanoTime()/1000000000d;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }
        }

    }

    public void checkCollisions() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obs = obstacles.get(i);
            if(ply.getX()+ply.getWidth() > obs.getX() && ply.getX()+ply.getWidth() < obs.getX()+obs.getWidth()) ply.setX(ply.getX()-5);
            if(ply.getX() < obs.getX()+obs.getWidth() && ply.getX() > obs.getX()) ply.setX(ply.getX()+5);
            if(ply.getY() < obs.getY()+ply.getHeight())ply.setY(ply.getY()-5); //TODO
            if(ply.getY() > obs.getY()+obs.getHeight()) {
                if(ply.getY() < obs.getY()+obs.getHeight()) ply.setY(ply.getY()+5);
            }
        }
    }


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

}


