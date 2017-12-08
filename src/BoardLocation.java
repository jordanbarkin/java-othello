public class BoardLocation {
	protected int x, y;
	
	public BoardLocation(int x, int y) {
		if(this.x >= Game.SIZE || this.y >=Game.SIZE)
			throw new IndexOutOfBoundsException();
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
