import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame{
	
	private int x;
	private int y;
	private int resolution;
	private JLabel[][] position;
	private LocalPlayer ply;
	
	public Game(int resolution) {
		this.resolution = resolution;
		System.out.println(resolution);
		ply = new LocalPlayer(50,50);
		initInterface();
	}
	
	public void initInterface() {
		
		setLayout(new GridLayout(resolution,resolution));
		setVisible(true);
		setResizable(true);
		setSize(600, 600);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Timer t = new Timer();
		Physics physics = new Physics();
		physics.setLocalPlayer(ply);
		physics.setGame(this);
		
		
		position = new JLabel[resolution][resolution];
		
		for(int lig = 0; lig < resolution; lig++) {
			for(int col = 0; col<resolution; col++) {
				position[lig][col] = new JLabel();
				add(position[lig][col]);
				position[lig][col].setBackground(Color.WHITE);
				position[lig][col].setOpaque(true);
			}
		}
		repaint();
		
		setPlayerPosition(ply.getX(),ply.getY());
		
		addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_W) setPlayerPosition(ply.getX(),ply.getY()-1);
	            if(e.getKeyCode() == KeyEvent.VK_S) setPlayerPosition(ply.getX(),ply.getY()+1);
	            if(e.getKeyCode() == KeyEvent.VK_A) setPlayerPosition(ply.getX()-1,ply.getY());
	            if(e.getKeyCode() == KeyEvent.VK_D) setPlayerPosition(ply.getX()+1, ply.getY());
	            
	            repaint();
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
		t.schedule(physics, 0, 1000);
	}
	
	public void setPlayerPosition(int x, int y) {
		
		System.out.println(x+","+y);
		
		position[y][x].setBackground(Color.BLACK);
		position[ply.getY()][ply.getX()].setBackground(Color.WHITE);
		ply.setX(x);
		ply.setY(y);
		validate();
		repaint();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	
}

