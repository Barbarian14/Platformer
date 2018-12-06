import java.util.TimerTask;

public class Physics extends TimerTask{
	
	private Vecteur vec =  new Vecteur(10,0);
	private Game game;
	private LocalPlayer ply;
	
	public void updateVec(Vecteur vec) {
				
	}
	public void gravity(Vecteur vec) {
		vec.setY(vec.getY()-2);
		game.setPlayerPosition( vec.getX(), vec.getY());
	}
	
	public void setGame(Game game) {
		
	}
	
	public void setLocalPlayer(LocalPlayer ply) {
		this.ply = ply;
	}
	
	@Override
	public void run() {
		if(vec == null) vec = new Vecteur();
		
		gravity(vec);
		
	}

}


