import java.util.Scanner;

public class VivaQ2 {
    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);
        System.out.print("Enter number:");
        String num = cs.nextLine();
        System.out.println("Largest number: " + Integer.parseInt(getLargestNum(num)));
        System.out.println("Smallest number: " + Integer.parseInt(getSmallestNum(num)));
    }

    public static String getLargestNum(String num) {
        String ans = "";
        // Store in char array alp
        char[] alp = new char[num.length()];
        for (int i = 0; i < num.length(); i++) {
            alp[i] = num.charAt(i);

        }

        // Check if is digit
        for (int i = 0; i < num.length(); i++) {
            if (alp[i] >= '0' && alp[i] <= '9')
                ans += alp[i];
        }

        // Build a StringBuilder to sort rearrange the number
        StringBuilder sb = new StringBuilder(ans);
        for (int pass = 0; pass < sb.length(); pass++) // bubble sort
        {
            for (int i = 0; i < ans.length() - 1; i++) {
                if (sb.charAt(i) <= sb.charAt(i + 1)) {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(i + 1));
                    sb.setCharAt(i + 1, temp);
                }
            }
        }
        return sb.toString();
    }

    public static String getSmallestNum(String num) {
        String ans = "";
        char[] alp = new char[num.length()];

        // Store in char array alp
        for (int i = 0; i < num.length(); i++) {
            alp[i] = num.charAt(i);

        }

        // Check if is digit
        for (int i = 0; i < num.length(); i++) {
            if (alp[i] >= '0' && alp[i] <= '9')
                ans += alp[i];
        }

        // Build a StringBuilder to sort rearrange the number
        StringBuilder sb = new StringBuilder(ans);

        // Build a StringBuilder to sort rearrange the number
        for (int pass = 0; pass < sb.length(); pass++)// bubble sort
        {
            for (int i = 0; i < ans.length() - 1; i++) {
                if (sb.charAt(i) >= sb.charAt(i + 1)) {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(i + 1));
                    sb.setCharAt(i + 1, temp);
                }
            }
        }

        return sb.toString();

    }

}