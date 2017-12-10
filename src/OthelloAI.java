public class OthelloAI {
	public static Move computeBestMove(Game game, Player player, int depth) throws InvalidMoveException {
		return NegaMax.search(game, player, Integer.MIN_VALUE, Integer.MAX_VALUE, depth).move;
	}
}
