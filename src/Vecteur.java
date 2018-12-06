public class Vecteur {
	
	private int x, y;
	public Vecteur() {
		this.x=0;
		this.y=0;
	}
	public Vecteur(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public double norme() {
		return Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
	}
	public double orientation() {
		return Math.acos(x/norme());
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


	
}
