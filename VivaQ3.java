import java.util.Scanner;

public class VivaQ3 {

    public static char[] originalChars = new char[50];
    public static char[] mirroredChars = new char[50];
    public static int pairCount = 0;

    // 1. Method to add a mirror pair to the arrays
    public static void addMirrorPair(char c1, char c2) {
        if (pairCount < originalChars.length) {
            originalChars[pairCount] = c1;
            mirroredChars[pairCount] = c2;
            pairCount++;
            // Add the reverse form
            originalChars[pairCount] = c2;
            mirroredChars[pairCount] = c1;
            pairCount++;
        }
    }

    // 2. Method to initialize default mirror pairs
    public static void initializeDefaultMirrorPairs() {
        char[] selfMirroring = { 'A', 'H', 'I', 'M', 'O', 'T', 'U', 'V', 'W', 'X', 'Y', 'o', 'u', 'v', 'w' };

        for (char c : selfMirroring) {
            if (pairCount < originalChars.length) {
                originalChars[pairCount] = c;
                mirroredChars[pairCount] = c;
                pairCount++;
            }
        }

        addMirrorPair('b', 'd');
        addMirrorPair('p', 'q');
    }

    // 3. Method to find length of palindrome (helper method)
    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // returns the length of palindrome (number)
        return right - left - 1;

    }

    // 4. Method to find longest palindrome
    public static String getLongestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "-";
        String longest = "";

        // For odd length palindromes (centre is i)
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            if (len1 > longest.length()) {
                int start = i - (len1 - 1) / 2;
                longest = s.substring(start, start + len1);
            }

            // For even length palindromes (centre is i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            if (len2 > longest.length()) {
                int start = i - (len2 - 1) / 2;
                longest = s.substring(start, start + len2);
            }
        }

        // Return "-" if no palindrome found
        return longest.length() < 2 ? longest : "-";
    }

    // 5. Method to find where if palindrome is mirrorable
    public static boolean isMirrorable(String s) {
        int n = s.length();
        // check from outside to inside
        for (int i = 0; i < (n + 1) / 2; i++) {
            char leftChar = s.charAt(i);
            char rightChar = s.charAt(n - 1 - i);

            boolean pairFound = false;
            // Find whether mirrored pair exists
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

    // 6. Method to find longest mirrorable palindrome
    public static String getLongestMirrorWord(String s) {
        if (s == null || s.length() < 1)
            return "-";
        String longestMirrorable = "";

        // Search through all possible substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (isMirrorable(sub) && sub.length() > longestMirrorable.length()) {
                    longestMirrorable = sub;
                }
            }
        }
        // Return "-" if no mirrorable string (length < 2 is found)
        return longestMirrorable.length() < 2 ? "-" : longestMirrorable;
    }

    // Main method: user input and finding palindromes
    public static void main(String[] args) {
        // 1. Initialise default mirror pairs
        initializeDefaultMirrorPairs();

        Scanner sc = new Scanner(System.in);

        // 2. Allow user to add up to 10 new mirror pairs
        System.out.println("Enter up to 10 new mirror pairs (e.g., 'b d'). Press Enter on an empty line to stop.");
        for (int i = 0; i < 10; i++) {
            System.out.print("Pair " + (i + 1) + ": ");
            String line = sc.nextLine();
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
        String inputWord = sc.nextLine();
        sc.close();

        // 4. Determine and display the longest palindrome and mirrorable substrings
        String longestPalindrome = getLongestPalindrome(inputWord);
        String longestMirrorable = getLongestMirrorWord(inputWord);

        System.out.println("Longest palindrome substring: " + longestPalindrome);
        System.out.println("Longest mirrorable substring: " + longestMirrorable);
    }
}