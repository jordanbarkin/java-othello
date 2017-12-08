import java.util.Scanner;

public class GameTester {
	public static void main(String[] args) throws InvalidMoveException {
		//		Game myGame = new Game();
		//		System.out.println(myGame);
		//
		//		Move testMove = new Move("4c",Player.BLACK, myGame);
		//		testMove.execute(myGame);
		//
		//		System.out.println(myGame);
		//
		//		Move testMove2 = new Move("3c",Player.WHITE, myGame);
		//		System.out.println(testMove2.isLegal);
		//		testMove2.execute(myGame);
		//		System.out.println(myGame);
		//
		//		Move testMove3 = new Move("6e",Player.BLACK, myGame);
		//		testMove3.execute(myGame);
		//		System.out.println(myGame);
		//		

		Game game = new Game();
		//		game.board[2][0] = Tile.WHITE;
		System.out.println(game);

		boolean blackTurn = true;
		Scanner in = new Scanner(System.in);
		while(!game.ended) {
			Player player = blackTurn? Player.BLACK:Player.WHITE;
			if(player == Player.WHITE)
				System.out.println("White move:");
			else
				System.out.println("Black move:");
			String moveDirection = in.next();
			try {
				Move currentMove = new Move(moveDirection, player, game);
				currentMove.execute(game);
				System.out.println(game);
				game.checkIfEnded();
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
		in.close();
	}
}
