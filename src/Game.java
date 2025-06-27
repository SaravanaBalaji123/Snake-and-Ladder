import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Game instance;
    private Board board;
    private Dice dice;
    private List<Player> players;
    private Notifications notificationSystem;
    private boolean isWon;

    public Board getBoard() {
        return board;
    }
//    static {
//
//    }
//
//    {
//
//    }

    Game(){
        board = new Board(100);
        dice = new Dice(1, 6);
        players = new ArrayList<>();
        notificationSystem = null;
        isWon = false;
    }


    public void addPlayers(List<Player> playersList){
        this.players = playersList;
        this.notificationSystem = new Notifications();
        notificationSystem.addPlayers(playersList);
    }

    public void makeMove(Player player) throws InterruptedException {
        int roll = dice.animatedRoll();
        System.out.println(player.getName()+ " has rolled a " + roll + ".");
        int newPostion = player.getPosition() + roll;
        if(newPostion > board.getSize()){
            newPostion = player.getPosition();
        }else{
            if(board.getSnakes().containsKey(newPostion)){
                newPostion = board.getSnakes().get(newPostion);
                System.out.println("Got bit by snake.");
            }else if(board.getLadders().containsKey(newPostion)){
                newPostion = board.getLadders().get(newPostion);
                System.out.println("Got up by ladder.");
            }
        }

        player.updatePostion(newPostion);
        System.out.println(player.getName()+ " has moved to " + newPostion + ".");
        if(newPostion  == board.getSize()){
            isWon = true;
            System.out.println("Game won by " + player.getName() + ".");
        }
//        else{
//             System.out.println("Game not won.");
//        }

    }

    public void StartGame() throws InterruptedException {
//        boolean skip = false;
        Scanner input = new Scanner(System.in);
        char c;
        while(!isWon){
            for(Player player : players){
                System.out.println(player.getName() + ", do want to roll the dice? ");
                c = input.next().charAt(0);
                if(c == 'y' || c == 'Y') {
                    makeMove(player);
                }
                else if(c == 'n' || c == 'N'){
                    System.out.println(player.getName() + " has started.");
                    break;
                }

                if(isWon)break;
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
