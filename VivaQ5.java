
import java.util.Scanner;
import java.util.Random;

public class VivaQ5 {
    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);

        // Input Statement
        System.out.print("Enter number length (3 or 4): ");
        int length = cs.nextInt();
        while (length < 3 || length > 4) {
            System.out.println("Invalid value. Please enter again:");
            length = cs.nextInt();
        }

        System.out.print("Enter number of ball to choose: ");
        int numOfBall = cs.nextInt();
        while (numOfBall < 0) {
            System.out.println("Cannot less than 1. Please enter again:");
            numOfBall = cs.nextInt();
        }

        System.out.print("Enter ball value: ");
        int ballValue = cs.nextInt();
        while (ballValue <= 0) {
            System.out.println("Cannot less than 1. Please enter again:");
            ballValue = cs.nextInt();
        }

        System.out.print("Target number: ");
        int target = cs.nextInt();
        while (length == 3 && (target < 0 || target > 999)) {
            System.out.println("Please enter valid number:");
            target = cs.nextInt();
        }

        while (length == 4 && (target < 0 || target > 9999)) {
            System.out.println("Please enter valid number:");
            target = cs.nextInt();
        }
        // Create array
        int[] num = generateNum(numOfBall, length);
        displayNum(num, numOfBall);
        boolean status = false;
        double totalPrize = (length * ballValue * 1000) / numOfBall;

        String nearMissList = "";
        for (int i = 0; i < num.length; i++) {
            status = foundTarget(target, num[i]);
            if (status == true) {
                System.out.println("Congratulations!! You Got The Number " + target);
                System.out.printf("RM%.2f", totalPrize);
                break;

            } else if (nearMiss(target, num[i], length)) {
                nearMissList = nearMissList + num[i] + " ";
            }
        }

        if (!nearMissList.equals("")) {
            System.out.println("You almost get it\n" + nearMissList);
        } else if (nearMissList.equals(""))
            System.out.println("Try again next time");
    }

    public static int[] generateNum(int numOfBall, int length) {
        Random rd = new Random();
        int[] num = new int[numOfBall];
        if (length == 3) {
            for (int i = 0; i < num.length; i++) {
                num[i] = rd.nextInt(1000);
            }
        } else if (length == 4) {
            for (int i = 0; i < num.length; i++) {
                num[i] = rd.nextInt(10000);
            }
        }
        return num;

    }

    public static void displayNum(int[] num, int numOfBall) {
        int colm = (int) Math.sqrt(numOfBall);
        int row = numOfBall / colm;

        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + "\t");
            if ((i + 1) % row == 0)
                System.out.println();
        }

    }

    public static boolean foundTarget(int target, int poolNumber) {
        if (target == poolNumber)
            return true;
        else
            return false;
    }

    public static boolean nearMiss(int target, int poolNumber, int length) {
        int count = 0;
        int tempTarget = target;
        int tempPool = poolNumber;
        for (int i = 0; i < length; i++) {
            if (tempTarget % 10 == (tempPool % 10)) {
                count++;
            }
            tempTarget /= 10;
            tempPool /= 10;
        }
        return (count == (length - 1));
    }
}
