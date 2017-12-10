public class AISearchResult {
	protected Move move;
	protected int score;
	
	public AISearchResult(Move move, int score) {
		this.score = score;
		this.move = move;
	}
	
	public AISearchResult negated() {
		return new AISearchResult(this.move, -score);
	}
}
