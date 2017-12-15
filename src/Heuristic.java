
public class Heuristic {
	public static int heuristic(Game game, Player player) { //modify this and this alone.
		Tile[][] board = game.board;
		int score = 0;

		//diagonals
		boolean hasDownDiagonal = true;
		for(int i = 0; i<board.length; i++) {
			if(!board[i][i].equals(player)) {
				hasDownDiagonal = false;
			}
		}

		boolean hasUpDiagonal = true;
		for(int i = 0; i<board.length; i++) {
			if(!board[i][board.length-1-i].equals(player)) {
				hasUpDiagonal = false;
			}
		}

		if(hasDownDiagonal) score += 20;
		if(hasUpDiagonal) score += 20;

		//rows
		for(int i = 0; i<board.length; i++) {
			boolean hasRow = true;
			for(int j = 0; j<board[i].length; j++) {
				if(!board[i][j].equals(player)) {
					hasRow = false;
				} else if(Math.abs(board.length-i)>i){
					score += (i+1)%2*(Math.max((5-i),0));
				} else {
					score += (board.length-1-i+1)%2*(Math.max((5-(board.length-1-i)),0));
				}
			}
			if(hasRow) score += 10;
		}

		//cols
		for(int i = 0; i<board.length; i++) {
			boolean hasCol = true;
			for (int j = 0; j < board[i].length; j++) {
				if (!board[j][i].equals(player)) {
					hasCol = false;
				}
			}
			if (hasCol) score += 10;
		}

		//even distances from outside are good, odd distances from outside are bad

		score+=game.getLegalMoveArray(player).size()*10;
		score+=game.getScore(player)*3;
		return score;
	}

}
