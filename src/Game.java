import java.util.ArrayList;

public class Game {
	public static final int SIZE = 8;

	protected boolean ended;
	protected int whiteScore, blackScore;
	protected ArrayList<Move> previousMoves;
	protected ArrayList<Tile[][]> previousStates;
	protected int moveCount;
	
	Tile[][] board = new Tile[SIZE][SIZE];

	public Game() {
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++)	{
				board[i][j] = Tile.EMPTY;
			}
		}
		
		board[3][3] = Tile.WHITE;
		board[3][4] = Tile.BLACK;
		board[4][3] = Tile.BLACK;
		board[4][4] = Tile.WHITE;

		this.ended = false;
		computeScores();
		this.previousMoves = new ArrayList<Move>();
		this.previousStates = new ArrayList<Tile[][]>();
	}

	@SuppressWarnings("unchecked")
	public Game(Game other) {
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++)
				board[i][j] = other.board[i][j];
		}
		computeScores();
		this.previousMoves = (ArrayList<Move>) other.previousMoves.clone();
		this.previousStates = (ArrayList<Tile[][]>) other.previousStates.clone();

	}

	public void computeScores() {
		whiteScore = 0;
		blackScore = 0;
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++)	{
				if(board[i][j] == Tile.WHITE)
					whiteScore++;
				else if(board[i][j] == Tile.BLACK)
					blackScore++;
			}

		}
	}

	public String toString() {	
		StringBuilder result = new StringBuilder();
		result.append("\n" + "\n" +  "    0 1 2 3 4 5 6 7" + "\n");
		result.append("    A B C D E F G H" + "\n");

		int rowMarker = 1;
		for(Tile[] row : board) {
			result.append((rowMarker-1) + " " + rowMarker++);
			for(Tile tile : row) {
				char current;
				if(tile == Tile.BLACK)
					current = 'B';
				else if(tile == Tile.WHITE)
					current = 'W';
				else
					current = ' ';
				result.append("|" + current);
			}
			result.append(("|"));
			result.append("\n" + "   -----------------" + "\n");
		}
		computeScores();
		result.append("\n" + "White: " + this.whiteScore + " —— Black:" + this.blackScore);
		result.append("\n" + "Move number: " + (moveCount +1));
		result.append("\n");
		result.append("________________________________________" + "\n");

		return result.toString();
	}

	public static Tile[][] deepCopyBoard(Tile[][] in) {
		Tile[][] result = new Tile[SIZE][SIZE];
		for(int i = 0; i<SIZE; i++) 
			for(int j = 0; j<SIZE; j++)
				result[i][j] = in[i][j];
		return result;

	}
	
	public Game deepCopy()  {
		Game newGame = new Game(this);
		return newGame;
	}
	
	
	public void updateEnded(Player player) {
		this.ended = getLegalMoveArray(player).size() == 0;
	}
	
	public int getScore(Player player) {
		return player == Player.BLACK? blackScore : whiteScore;
	}

	public ArrayList<Move> getLegalMoveArray(Player player){ 
		ArrayList<Move> results = new ArrayList<Move>(64);

		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++)	{
				Move current = new Move(i,j,player, this);
				if(current.isLegal)
					results.add(current);
			}
		}
		return results; 
	}
}
