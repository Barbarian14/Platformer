import java.util.ArrayList;

public class GameContainer implements Runnable{

    private Thread thread;
    private boolean isRunning = false;
    private final double UPDATE_RATE = 1d/60d;
    private int gravityCounter = 0;

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
        obstacles.add(new Obstacle("player.png", 300,500));
        obstacles.add(new Obstacle("player.png", 500,600));
        obstacles.add(new Obstacle("player.png", 1500,400));
        obstacles.add(new Obstacle("player.png", 1300,100));
        obstacles.add(new Obstacle("player.png", 1100,111));
        obstacles.add(new Obstacle("player.png", 900,800));
        obstacles.add(new Obstacle("player.png", 700,562));
        obstacles.add(new Obstacle("player.png", 500,410));
        obstacles.add(new Obstacle("player.png", 600,300));
        obstacles.add(new Obstacle("player.png", 100,330));

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

        firstTime = System.nanoTime() / 1000000000d;

        while (isRunning) {
            lastTime = System.nanoTime() / 1000000000d;

            if ((lastTime - firstTime) >= UPDATE_RATE) {
                gravityCounter++;
                if (10 == gravityCounter) {
                    gravityCounter = 0;
                    ply.setGravity(ply.getGravity() + 1);
                }

                    if (ply.getX() >= 0 && ply.getY() >= 0 && ply.getWidth() <= gameWindow.getCanvas().getWidth() && ply.getHeight() <= gameWindow.getCanvas().getHeight()) {

                        ply.setY(ply.getY() + ply.getGravity());
                        while (true) {
                            if (hasCollision()) {
                                int i = hasCollisionWith();
                                if(obstacles.get(i).getY() > ply.getY()) {
                                    ply.setY(ply.getY() - 1);
                                    ply.setGravity(0);
                                    ply.setJumping(false);
                                } else if(obstacles.get(i).getY()< ply.getY()){
                                    ply.setY(ply.getY() + 1);
                                    ply.setGravity(0);
                                }

                                continue;
                            }
                            break;
                        }
                        if(ply.getY()+ply.getHeight() == gameWindow.getCanvas().getHeight()) {
                            ply.setGravity(0);
                            ply.setJumping(false);
                        }
                        if(ply.getGravity()>0) ply.setJumping(true);

                        if (input.isMovingUp()) {

                            if(!ply.getJumping()) {
                                ply.setGravity(-5);
                                ply.setY(ply.getY() - ply.getSpeed());
                                while (true) {
                                    if (hasCollision()) {
                                        ply.setY(ply.getY() + 1);
                                        continue;
                                    }
                                    break;
                                }
                                ply.setJumping(true);
                            }
                        }
                        if (input.isMovingLeft()) {
                            ply.setX(ply.getX() - ply.getSpeed());
                            while (true) {
                                if (hasCollision()) {
                                    ply.setX(ply.getX() + 1);
                                    continue;
                                }
                                break;
                            }
                        }
                        if (input.isMovingRight()) {
                            ply.setX(ply.getX() + ply.getSpeed());
                            while (true) {
                                if (hasCollision()) {
                                    ply.setX(ply.getX() - 1);
                                    continue;
                                }
                                break;
                            }
                        }

                    } else {
                        System.out.println("test");
                        ply.setJumping(false);
                    }
                    renderer.clear();
                    renderer.drawImage(ply);
                    renderer.drawObstacles(obstacles);

                    gameWindow.update();
                    firstTime = System.nanoTime() / 1000000000d;
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }


        }


    public boolean hasCollision() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obs = obstacles.get(i);
            if(ply.getHitbox().intersects(obs.getHitbox())) return true;

        }
        return false;
    }

    public int hasCollisionWith() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obs = obstacles.get(i);
            if(ply.getHitbox().intersects(obs.getHitbox())) return i;

        }
        return -1;
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


