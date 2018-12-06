
public class LocalPlayer {
	private int x, y;
	
	public LocalPlayer() {
		this.x= 50;
		this.y= 50;
	}
	public LocalPlayer(int x, int y) {
		this.x = x;
		this.y = y;
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

	public LocalPlayer getLocalPlayer() {
		return this;
	}
}
