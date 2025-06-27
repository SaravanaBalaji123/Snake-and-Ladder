import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Math.*;

public class Board {
    private int size = 100;
    private final Map<Integer, Integer> Snakes;
    private final Map<Integer, Integer> Ladders;

    Board(){
        this.Snakes = new HashMap<>();
        this.Ladders = new HashMap<>();
    }
    
    Board(int size){
        this.size = size;
        this.Snakes = new HashMap<>();
        this.Ladders = new HashMap<>();
    }

    // Checks if current insert of snake or ladder is valid.
    public boolean validateInsertSnakeorLadder(int start, int end){
        return  Snakes.containsKey(start) || Ladders.containsKey(start)
                || Snakes.containsValue(start) || Ladders.containsValue(start)
                || Snakes.containsKey(end) || Ladders.containsKey(end);
    }

    public boolean addSnake(int start, int end){
        if(start < end){
            int temp = start;
            start = end;
            end = temp;
        }
        if(start/10 - end/10 > 0 && start < size && end > 0 && start > sqrt(size)){
            // I am going to add a snake.
            // Does this make my board invalid?
            // What all makes my board invalid.
            // Two entities starts meeting
            // Two entities where one end meets with other start.
            if(validateInsertSnakeorLadder(start, end)){
                System.out.println("Conflicting Snake placement!");
                return false;
            }
            Snakes.put(start, end);
            return true;
        }
        System.out.println("Out of Bounds");
        return false;
    }

    public boolean addLadder(int start, int end){
        if(start > end){
            int temp = start;
            start = end;
            end = temp;
        }
        if(end/sqrt(size)- start/sqrt(size) > 0 && end < size && start > 0 && end < size - sqrt(size)) {
            if(validateInsertSnakeorLadder(start, end)){
                System.out.println("Conflicting Ladder placement!");
                return false;
            }
            Ladders.put(start, end);
            return true;
        }
        System.out.println("Out of Bounds");
        return false;
    }

    public boolean setOwnBoard( ){
        System.out.println("Enter the size of the board, available range of Size 20 to 500 that is square root: ");
        Scanner sc = new Scanner(System.in);
        long size = sc.nextInt();
        if(size < 20 || size > 500) {
            System.out.println("Invalid size try again");
            return false;
        }
        long sqrt = (long) Math.sqrt(size);
        if(sqrt*sqrt != size){
            System.out.println("Invalid size, not a square");
            return false;
        }
        System.out.println("Enter the number of snakes: ");
        int count_snakes = sc.nextInt();
        // Not handled the case where he enters negative ladders.
        while(count_snakes != 0) {
                System.out.println("Enter the snake head: ");
                int start = sc.nextInt();
                System.out.println("Enter the tail of snake: ");
                int end = sc.nextInt();
                if(!this.addSnake(start, end)) {
                    System.out.println("Invalid snake try again");
                    continue;
                }
                System.out.println("Snake set on" + start +", "+ end + ".");
                count_snakes--;
        }
        System.out.println("Enter the number of Ladders: ");
        int count_ladders = sc.nextInt();
        // Not handled the case where he enters negative ladders.
        while(count_ladders != 0) {
            System.out.println("Enter the Ladder Tail: ");
            int start = sc.nextInt();
            System.out.println("Enter the Ladder Head: ");
            int end = sc.nextInt();
            if(!this.addLadder(start, end)) {
                System.out.println("Invalid Ladder try again");
                continue;
            }
            System.out.println("Ladder set on" + start +", "+ end + ".");
            count_ladders--;
        }

        return true;
    }

    public int findShortestPath(){
        int start = 0;
        // We can do with overlapping intervals problem, or interval scheduling greedy approach
        // Dfs is not much useful here
        // does all paths < size? no.
        // This can be treated as a graph with each node having neighbours(+1...+6)
        // Special nodes have neighbours across the map.
        // For normal nodes
        // I am just going do Djikstra's Algorithm
        int[] distance = new int[size + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        for(int index = 0; index < size; index++){
            for(int jump = 1; jump <= 6 && index + jump <= size; jump++){
                distance[index+jump] = min(distance[index] + jump, distance[index + jump]);
            }
            if(Snakes.containsKey(index)){
                int tail = Snakes.get(index);
                distance[tail] = min(distance[tail], distance[index]);
            }// Index contains a snakes
            // Comment this out for if you want shortest parth only with ladders.
            if(Ladders.containsKey(index)){
                int head = Ladders.get(index);
                distance[head] = min(distance[head], distance[index]);
            }// Index contains a Ladder
        }
        return distance[size];
    }

    public int shortestNumberOfMoves(){
        return 0;
    }

    public int getSize(){return size;}
    public Map<Integer, Integer> getSnakes(){return Snakes;}
    public Map<Integer, Integer> getLadders(){return Ladders;}

}
