public enum Player {
	WHITE,
	BLACK;
	
	public  Player opponent() {
		return this == Player.WHITE? Player.BLACK : Player.WHITE;
	}
}
