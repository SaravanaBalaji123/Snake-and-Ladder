
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Setup game
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        Board board = game.getBoard();

        boolean setBoard = false;
        while(!setBoard) {
            System.out.println("Do you want to make a custom board? (Y/N)");
            char choice = scanner.next().charAt(0);

            if(choice == 'Y' || choice == 'y'){
                Board ownBoard = new Board();
                setBoard = ownBoard.setOwnBoard();
                if(!setBoard){
                    ownBoard = null;
                    // Delete board to destroy.
                }
                game.setBoard(ownBoard);
            }
            else if(choice == 'N' || choice == 'n') {
                // Manually set up the board.
                board.addSnake(87, 75);
                board.addSnake(98, 30);
                board.addSnake(65, 39);
                board.addSnake(67, 29);
                board.addSnake(25, 13);

                board.addLadder(31, 91);
                board.addLadder(12, 35);
                board.addLadder(44, 62);
                board.addLadder(47, 71);

                setBoard = true;
            }
            else{
                System.out.println("Invalid answer for the question.");
            }
        }

        System.out.println("Board Ready!");
        Board finalBoard = game.getBoard();
        System.out.println("The minimum path length to complete this course: " + finalBoard.findShortestPath());
        // Create players
        List<Player> players = new ArrayList<>();


        System.out.print("Enter number of players: ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        String name;
        for(int i = 1; i <= playerCount; i++){
            System.out.print("Player " + i + " enter your name: ");
            name = scanner.nextLine();
            Player p = new Player(i, name);
            players.add(p);
        }

        game.addPlayers(players);

        System.out.println("--------Game has started-------");

        // Start gameplay
        game.StartGame();

        System.out.println("--------Game has ended-------");
    }
}
