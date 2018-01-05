public class Move {
	protected BoardLocation coordinate;
	protected Player player;
	protected Tile tileColor;
	protected boolean isLegal;

	public Move(BoardLocation coordinate, Player player, Game currentGame) {
		this.coordinate = coordinate;
		this.player = player;
		if(this.player == Player.BLACK)
			this.tileColor = Tile.BLACK;
		else
			this.tileColor = Tile.WHITE;
		this.isLegal = computeLegality(currentGame);
	}

	public Move(int x, int y, Player player, Game currentGame) {
		this(new BoardLocation(x,y), player, currentGame);
	}

	public Move(String location, Player player, Game currentGame) {
		this(getCoordinateFromString(location),player,currentGame);
	}

	public static BoardLocation getCoordinateFromString(String input) {
		int xCoord = Integer.parseInt("" + input.charAt(0));
		int yCoord = input.charAt(1) - 'a' +1;
		return new BoardLocation(yCoord-1,xCoord-1);
	}

	public static String getStringFromCoordinate(BoardLocation input) {
		int xCoord = input.y+1;
		int yCoord = input.x+1;
		char y = (char) (yCoord + 'a' -1);
		return xCoord + "" + y;
	}

	private boolean computeLegality(Game game) {
		boolean result = false;
		if(game.board[coordinate.y][coordinate.x] == Tile.EMPTY) {
			int posX, posY;
			boolean found;
			Tile current;

			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if(x == 0 && y == 0)
						continue;

					posX = coordinate.x + x;
					posY = coordinate.y + y;
					found = false;

					if(posX < 0 || posY<0 || posX >= Game.SIZE ||  posY >= Game.SIZE )
						continue;

					current = game.board[posY][posX];

					if (current == Tile.EMPTY || current == this.tileColor)
						continue;


					while (!found)
					{
						posX += x;
						posY += y;

						if (posX < 0 || posY<0 || posX >= Game.SIZE ||  posY >= Game.SIZE )
							found = true;
						else {
							current = game.board[posY][posX];

							if(current == Tile.EMPTY)
								found = true;

							if (current == this.tileColor)
								return true;

						}
					}
				}
			}
		}
		return result;      

	}

	public void execute(Game game) throws InvalidMoveException {
		if(!this.isLegal)
			throw new InvalidMoveException();
		else {
			game.moveCount++;
			game.previousStates.add(Game.deepCopyBoard(game.board));

			if(game.board[coordinate.y][coordinate.x] == Tile.EMPTY) {
				int posX, posY;
				boolean found;
				Tile current;

				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {
						if(x == 0 && y == 0)
							continue;

						posX = coordinate.x + x;
						posY = coordinate.y + y;
						found = false;

						if(posX < 0 || posY<0 || posX >= Game.SIZE ||  posY >= Game.SIZE )
							continue;

						current = game.board[posY][posX];

						if (current == Tile.EMPTY || current == this.tileColor)
							continue;


						while (!found)
						{
							posX += x;
							posY += y;

							if (posX < 0 || posY<0 || posX >= Game.SIZE ||  posY >= Game.SIZE )
								found = true;
							else {

								current = game.board[posY][posX];

								if(current == Tile.EMPTY)
									found = true;

								if (current == this.tileColor) {
									while(current != Tile.EMPTY) {
										game.board[posY][posX] = tileColor;
										posX -= x;
										posY -= y;
										current = game.board[posY][posX];


									}
									found = true;

								}
							}
						}
					}
				}
			}

			game.board[this.coordinate.y][this.coordinate.x] = this.tileColor;
			game.previousMoves.add(this);
			Player otherPlayer = this.player == Player.WHITE? Player.BLACK : Player.WHITE;
			game.updateEnded(otherPlayer, player);
		}
	}
}


