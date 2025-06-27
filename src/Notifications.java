import java.util.List;

public class Notifications {
    private List<Player> players;

    public void addPlayers(List<Player> players){
        this.players = players;
    }

    public void NotifyAll(String message){
        for(Player player :players){
            player.Notify(message);
        }
    }
}
