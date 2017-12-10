public class BoardLocation {
	protected int x, y;
	
	public BoardLocation(int x, int y) {
		if(x >= Game.SIZE || y >=Game.SIZE || x<0 || y<0)
			throw new IndexOutOfBoundsException();
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
