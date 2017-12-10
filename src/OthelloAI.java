import java.util.ArrayList;

public class OthelloAI {
	public static Move computeBestMove(Game game, Player player) {
		ArrayList<Move> possibleMoves = game.getLegalMoveArray(player);
		int i = (int)(Math.random()*possibleMoves.size());
		return possibleMoves.get(i);
	}
}
