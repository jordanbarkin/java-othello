import java.util.ArrayList;

public class NegaMax {
	public static AISearchResult search(Game game, Player player, int alpha, int beta, int depth) throws InvalidMoveException {
		if(depth<=0 || game.ended)
			return new AISearchResult(null, Heuristic.heuristic(game, player));
		else {
			ArrayList<Move> possibleMoves = game.getLegalMoveArray(player);
			AISearchResult best = new AISearchResult(null, alpha);
			if(possibleMoves.isEmpty()) {
				possibleMoves = game.getLegalMoveArray(player.opponent());
				if(possibleMoves.isEmpty()) {
					switch(Integer.signum(game.getScore(player) - game.getScore(player.opponent())))  {
					case -1:
						best = new AISearchResult(null, Integer.MIN_VALUE);
						break;
					case 0:
						best = new AISearchResult(null, 0);
						break;
					case 1:
						best = new AISearchResult(null, Integer.MAX_VALUE);
						break;
					} 
				}
				else {
					best = search(game.deepCopy(), player.opponent(), -beta, -alpha, depth-1).negated();
				}
			}
			else {
				for(Move possibleMove : possibleMoves) {
					Game testGame = game.deepCopy();
					possibleMove.execute(testGame);
					int score = search(testGame, player.opponent(), -beta, -alpha, depth - 1).negated().score;
					if (alpha < score) {
						alpha = score;
						best = new AISearchResult(possibleMove, score);
					}
					if (alpha >= beta) {
						return best;
					}
				}
			}
			return best;
		}

	}



}
