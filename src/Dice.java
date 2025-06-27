import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Dice {
    private int min;
    private int max;
    private Random rand;
    // Dice face art (for 1-6, min/max must be set accordingly)
    private static final String[] DICE_FACES = {
            "-------\n|     |\n|  *  |\n|     |\n-------",   // 1
            "-------\n|*    |\n|     |\n|    *|\n-------",   // 2
            "-------\n|*    |\n|  *  |\n|    *|\n-------",   // 3
            "-------\n|*   *|\n|     |\n|*   *|\n-------",   // 4
            "-------\n|*   *|\n|  *  |\n|*   *|\n-------",   // 5
            "-------\n|* * *|\n|     |\n|* * *|\n-------"    // 6
    };

    {
        min = 0;
        max = 6;
    }

    public Dice(){
        this.min = 0;
        this.max = 6;
        this.rand = new Random();
    }

    public Dice(int min, int max){
        this.min = min;
        this.max = max;
        rand = new Random();
    }

    public int roll(){
        int roll =  rand.nextInt(max - min + 1) + min;
        return roll;
    }
//
    private void printDiceFace(int value) {
        if (value >= 1 && value <= 6) {
            System.out.println(DICE_FACES[value - 1]);
        } else {
            System.out.println("No art for value " + value);
        }
    }

    public int animatedRoll() throws InterruptedException {
        System.out.println("Rolling the dice...");
        for (int i = 0; i < 5; i++) {
            int face = rand.nextInt(6);
            printDiceFace(face + 1);
            TimeUnit.MILLISECONDS.sleep(200);
            if (i < 7) {
                // ANSI escape code to move cursor up (works in most terminals)
                System.out.print("\033[7A");
            }
        }
        int result = roll();
        System.out.println("\nFinal result: " + result);
        printDiceFace(result);
        return result;
    }

}
