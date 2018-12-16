import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean isMovingLeft=false, isMovingRight=false, isMovingUp=false, isMovingDown=false;

    public Input(GameContainer gc) {
        gc.getGameWindow().getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            isMovingLeft=true;
        }
        if(e.getKeyCode()== KeyEvent.VK_S){
            isMovingDown=true;
        }
        if(e.getKeyCode()== KeyEvent.VK_D){
            isMovingRight=true;
        }
        if(e.getKeyCode()== KeyEvent.VK_W){
            isMovingUp=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            isMovingLeft=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_S){
            isMovingDown=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_D){
            isMovingRight=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_W){
            isMovingUp=false;
        }
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public void setMovingUp(boolean movingUp) {
        isMovingUp = movingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDown(boolean movingDown) {
        isMovingDown = movingDown;
    }
}
