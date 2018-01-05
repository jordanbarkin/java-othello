import java.util.Scanner;

public class GameTester {
	public static void main(String[] args) throws InvalidMoveException {
		Game game = new Game();
		boolean blackTurn = false;
		final int depth = 5;
		Scanner in = new Scanner(System.in);
		int gameMode = 0; // 1, 2, or 3

		while(gameMode == (0)) {
			System.out.println("Select Game Mode:" + "\n" + "1) Player vs. Player" + "\n" + "2) Player vs. AI"+ "\n"  + "3) AI vs. AI");
			String gameModeSelection = in.next();
			try {
				gameMode = Integer.parseInt(gameModeSelection);
				if(gameMode <=0 || gameMode >3)
					gameMode = 0;
				throw new Exception();
			}
			catch(Exception e) {
				System.out.println("Invalid game mode. Pick again");
			}
		}
		System.out.println(game);

		if(gameMode == 1) {
			while(!game.ended) {
				Player player = blackTurn? Player.BLACK:Player.WHITE;
				if(game.getLegalMoveArray(player).size() == 0) {
					blackTurn = !blackTurn;
					continue;
				}

				if(player == Player.WHITE)
					System.out.println("White move:");
				else
					System.out.println("Black move:");
				String moveDirection = in.next();
				try {
					Move currentMove = new Move(moveDirection, player, game);
					currentMove.execute(game);
					System.out.println(game);
					blackTurn = !blackTurn;
				}
				catch(InvalidMoveException e) { 
					System.out.println("Invalid move. Try again.");
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid format entry. Try again.");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid entry. Try again.");
				}

			}
			System.out.println(game);
			System.out.println("Game ended");
			in.close();
		}
		else if(gameMode == 2) {
			while(!game.ended) {
				Player player = blackTurn? Player.BLACK:Player.WHITE;
				if(game.getLegalMoveArray(player).size() == 0) {
					blackTurn = !blackTurn;
					continue;
				}
				String moveDirection = "";
				if(player == Player.WHITE) {
					System.out.println("White move:");
					moveDirection = in.next();
				}
				try {
					Move currentMove;
					if(player == Player.WHITE)	
						currentMove = new Move(moveDirection, player, game);
					else {
						currentMove = OthelloAI.computeBestMove(game, player, depth);
						System.out.print(player + ": " + Move.getStringFromCoordinate(currentMove.coordinate));
					}
					currentMove.execute(game);
					System.out.println(game);
					blackTurn = !blackTurn;
				}
				catch(InvalidMoveException e) { 
					System.out.println("Invalid move. Try again.");
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid entry. Try again.");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid entry. Try again.");
				}

			}
			System.out.println(game);
			System.out.println("Game ended");
			in.close();
		}
		else if(gameMode == 3) {
			while(!game.ended) {
				Player player = blackTurn? Player.BLACK:Player.WHITE;
				if(game.getLegalMoveArray(player).size() == 0) {
					blackTurn = !blackTurn;
					continue;
				}
				Move currentMove = OthelloAI.computeBestMove(game, player, depth);
				System.out.print(player + ": " + Move.getStringFromCoordinate(currentMove.coordinate));
				try {
					currentMove.execute(game);
					System.out.println(game);
					blackTurn = !blackTurn;
				}
				catch(InvalidMoveException e) { 
					System.out.println("Invalid move. Try again.");
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid entry. Try again.");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid entry. Try again.");
				}

			}
			System.out.println("Game ended");
			System.out.println("Final score: " + "White: " + game.whiteScore + " —— Black:" + game.blackScore);
			
			if(game.whiteScore > game.blackScore)
				System.out.println("White wins!");
			else if(game.whiteScore < game.blackScore)
				System.out.println("Black wins!");
			else
				System.out.println("Tie game!");
			
			in.close();
		}

	}
}

