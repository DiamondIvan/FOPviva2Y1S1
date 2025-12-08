import java.util.Scanner;

public class VivaQ3 {

    // Use parallel arrays instead of a Map to store mirror pairs.
    // Max pairs: 14 self-mirroring + (6*2) paired + (10*2) user-added = 46. Size 50
    // is safe.
    private static char[] originalChars = new char[50];
    private static char[] mirroredChars = new char[50];
    private static int pairCount = 0;

    public static void main(String[] args) {
        // 1. Initialize default mirror pairs
        initializeDefaultMirrorPairs();

        Scanner scanner = new Scanner(System.in);

        // 2. Allow user to add up to 10 new mirror pairs
        System.out.println("Enter up to 10 new mirror pairs (e.g., 'a b'). Press Enter on an empty line to stop.");
        for (int i = 0; i < 10; i++) {
            System.out.print("Pair " + (i + 1) + ": ");
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split("\\s+");
            if (parts.length == 2 && parts[0].length() == 1 && parts[1].length() == 1) {
                char c1 = parts[0].charAt(0);
                char c2 = parts[1].charAt(0);
                addMirrorPair(c1, c2); // Add the reverse form automatically
                System.out.println("Added pair: " + c1 + " <-> " + c2);
            } else {
                System.out.println("Invalid input. Please enter two characters separated by a space.");
                i--; // Decrement to re-prompt for the same pair number
            }
        }

        // 3. Read a string from the user
        System.out.print("\nEnter a word to analyze: ");
        String inputWord = scanner.nextLine();
        scanner.close();

        // 4. Determine and display the longest palindrome and mirrorable substrings
        String longestPalindrome = getLongestPalindrome(inputWord);
        String longestMirrorable = getLongestMirrorWord(inputWord);

        System.out.println("Longest palindrome substring: " + longestPalindrome);
        System.out.println("Longest mirrorable substring: " + longestMirrorable);
    }

    public static void addMirrorPair(char c1, char c2) {
        if (pairCount < originalChars.length - 1) {
            originalChars[pairCount] = c1;
            mirroredChars[pairCount] = c2;
            pairCount++;
            // Add the reverse form
            originalChars[pairCount] = c2;
            mirroredChars[pairCount] = c1;
            pairCount++;
        }
    }

    public static void initializeDefaultMirrorPairs() {
        // Self-mirroring characters
        char[] selfMirroring = { 'A', 'H', 'I', 'M', 'O', 'T', 'U', 'V', 'W', 'X', 'Y', '1', '8', '0', 'i', 'l', 'm',
                'o', 't', 'u', 'v', 'w', 'x' };
        for (char c : selfMirroring) {
            // For self-mirroring, we only need one entry, but our helper adds two.
            // To simplify, we can add them directly.
            if (pairCount < originalChars.length) {
                originalChars[pairCount] = c;
                mirroredChars[pairCount] = c;
                pairCount++;
            }
        }

        // Paired mirroring characters
        addMirrorPair('b', 'd');
        addMirrorPair('p', 'q');
        addMirrorPair('E', '3');
        addMirrorPair('S', '5');
        addMirrorPair('Z', '2');
        addMirrorPair('L', 'J');
    }

    // Find Longest palindromic Substring in a given String
    public static String getLongestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "-";
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (center is i)
            int len1 = expandAroundCenter(s, i, i); // Odd length palindromes
            if (len1 > longest.length()) {
                int start = i - (len1 - 1) / 2;
                longest = s.substring(start, start + len1);
            }

            // Even length palindromes (center is between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1); // Even length palindromes
            if (len2 > longest.length()) {
                int start = i - (len2 - 1) / 2;
                longest = s.substring(start, start + len2);
            }
        }

        // Return "-" if no valid palindrome (length < 2) is found
        return longest.length() < 2 ? "-" : longest;
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // The length of the palindrome is right - left - 1
        return right - left - 1;
    }

    // Find longest mirrorable substring in a given String
    public static String getLongestMirrorWord(String s) {
        if (s == null || s.length() < 1)
            return "-";

        String longestMirrorable = "";

        // Iterate through all possible substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (isMirrorable(sub) && sub.length() > longestMirrorable.length()) {
                    longestMirrorable = sub;
                }
            }
        }

        // Return "-" if no valid mirrorable string (length < 2) is found
        return longestMirrorable.length() < 2 ? "-" : longestMirrorable;
    }

    public static boolean isMirrorable(String s) {
        int n = s.length();
        // Loop from the outside in. (n + 1) / 2 handles both odd and even lengths
        // correctly and efficiently.
        for (int i = 0; i < (n + 1) / 2; i++) {
            char leftChar = s.charAt(i);
            char rightChar = s.charAt(n - 1 - i);

            boolean pairFound = false;
            // Find if a valid mirror pair exists
            for (int k = 0; k < pairCount; k++) {
                if (originalChars[k] == leftChar && mirroredChars[k] == rightChar) {
                    pairFound = true;
                    break;
                }
            }

            if (!pairFound) {
                return false;
            }
        }
        return true;
    }
}