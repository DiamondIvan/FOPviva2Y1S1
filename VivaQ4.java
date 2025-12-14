import java.util.Scanner;

public class VivaQ4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a message: ");
        String text = "";

        while (true) {
            String line = sc.nextLine(); // reads one full line of input from the Scanner
            if (line.isEmpty()) {
                break;
            } // if user press blank on empty line, loop will stop
            text += line + " ";
        }

        // Remove the trailing space added after the last line
        text = text.trim();

        System.out.println("Word Count: " + wordCount(text));
        System.out.println("Character Count: " + characterCount(text));
        System.out.println("Character Count without Space: " + characterCountWithoutSpaces(text));
        System.out.println("Sentence Count: " + sentenceCount(text));
        System.out.println("Most Frequent Word: " + mostFrequentWord(text));
        System.out.println("Longest Word: " + longestWord(text));

        sc.close();
    }

    public static int wordCount(String str) {
        if (str.trim().isEmpty())
            return 0;
        String[] words = str.trim().split("\\s+");
        return words.length;
    }

    public static int characterCount(String str) {
        return str.length();
    }

    public static int characterCountWithoutSpaces(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ')
                count++;
        }
        return count;
    }

    public static int sentenceCount(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.' || c == '!' || c == '?')
                count++;
        }
        return count;
    }

    public static String mostFrequentWord(String str) {
        String cleaned = str.toLowerCase().replaceAll("[^a-z\\s]", "");
        String[] words = cleaned.trim().split("\\s+");

        int maxCount = 0;
        String frequent = words[0];

        for (int i = 0; i < words.length; i++) {
            int count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                frequent = words[i];
            }
        }
        return capitalize(frequent);
    }

    public static String longestWord(String str) {
        String cleaned = str.replaceAll("[^a-zA-Z\\s]", "");
        String[] words = cleaned.trim().split("\\s+");

        String longest = words[0];

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }

        return longest;
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}