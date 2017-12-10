public class OthelloAI {
	public static Move computeBestMove(Game game, Player player) throws InvalidMoveException {
		return NegaMax.search(game, player, Integer.MIN_VALUE, Integer.MAX_VALUE, 6).move;
	}
}
