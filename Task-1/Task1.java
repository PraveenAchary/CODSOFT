// Number Game
import java.util.Random;
import java.util.Scanner;

public class Task1
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int min = 1;
        int max = 100;

        // 🎮 Print clear instructions
        System.out.println("======================================");
        System.out.println("          WELCOME TO NUMBER GAME       ");
        System.out.println("======================================");
        System.out.println("Instructions:");
        System.out.println("1. The computer will choose a random number between 1 and 100.");
        System.out.println("2. You have to guess that number.");
        System.out.println("3. After each guess:");
        System.out.println("   - You'll be told whether you are NEAR or FAR.");
        System.out.println("   - You'll get hints to guess higher or lower.");
        System.out.println("4. Each wrong guess costs you 2 points.");
        System.out.println("5. Each correct guess gives you +10 points.");
        System.out.println("Let's begin!\n");

        System.out.println("Enter no. of rounds you want to play:");
        int n = input.nextInt();
        int score = 10;

        for (int i = 1; i <= n; i++)
        {
            int uv = -1;
            int av = rand.nextInt((max - min) + 1) + min;
            System.out.println("\nA random number was produced. Start trying to guess it! Round - " + i);

            while (av != uv)
            {
                System.out.println("Enter your guess number:");
                uv = input.nextInt();

                if (Math.abs(av - uv) <= 10)
                    System.out.println("Near to it");
                else
                    System.out.println("Far away to it");

                score -= 2;

                if (uv < av)
                    System.out.println("Try a higher number!");
                else if (uv > av)
                    System.out.println("Try a lower number!");
            }

            System.out.println("You guessed it!");
            score += 10;
            System.out.println("Score for round - " + i + " is " + score);
        }

        System.out.println("\nFinal Score: " + score);
        input.close();
    }
}
