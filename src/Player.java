import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private final String name;
    private int pos;
    private final List<String> notification;
    private static int playerCount = 0;

    public Player(int id, String name){
        this.id = id;
        this.name = name;
        pos = 0;
        notification = new ArrayList<>();
        playerCount++;
    }

    public void updatePostion(int npos){
        pos = npos;
    }

    public int getPos() {
        return pos;
    }

    public List<String> getNotification() {
        return notification;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public void Notify(String Message){
        notification.add(Message);
        System.out.println(Message);
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public int getPosition(){return pos;}
    public List<String> getNotificationHistory(){return notification;}

}
