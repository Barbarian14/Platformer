import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {

	
	private int x;
	private int y;
	private int resolution;
	private JFrame frame;
	private JLabel[][] position;
	private LocalPlayer ply;
	
	public Game(int resolution) {
		this.resolution = resolution;
		ply = new LocalPlayer();
		initInterface();
	}
	
	public void initInterface() {
		
		frame = new JFrame("Game");
		frame.setLayout(new GridLayout(resolution,resolution));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_W) setPlayerPosition(0,-1);
	            if(e.getKeyCode() == KeyEvent.VK_S) setPlayerPosition(0,1);
	            if(e.getKeyCode() == KeyEvent.VK_A) setPlayerPosition(-1,0);
	            if(e.getKeyCode() == KeyEvent.VK_D) setPlayerPosition(1,0);
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
		
		position = new JLabel[resolution][resolution];
		
		for(int lig = 0; lig < resolution; lig++) {
			for(int col = 0; col<resolution; col++) {
				frame.add(position[lig][col]);
				position[lig][col].setBackground(Color.WHITE);
			}
		}
		frame.pack();
		frame.repaint();
		
	}
	
	public void setPlayerPosition(int x, int y) {
		
		position[ply.getY()+y][ply.getX()+x].setBackground(Color.BLACK);
		position[ply.getY()][ply.getX()].setBackground(Color.WHITE);
		ply.setX(ply.getX()+x);
		ply.setY(ply.getY()+y);
		
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
