import java.util.Scanner;

public class VivaQ3 {

    // Store mirror pairs
    public static char[] original = new char[50];
    public static char[] mirror = new char[50];
    public static int count = 0;

    // 1. Method to add mirror pairs
    public static void addMirrorPair(char a, char b) {
        original[count] = a;
        mirror[count] = b;
        count++;

        original[count] = b;
        mirror[count] = a;
        count++;
    }

    // 2. Method to initialize default mirror pairs
    public static void initDefaultPairs() {
        char[] self = { 'A', 'H', 'I', 'M', 'O', 'T', 'U', 'V', 'W', 'X', 'Y', 'o', 'u', 'v', 'w' };
        for (int i = 0; i < self.length; i++) {
            original[count] = self[i];
            mirror[count] = self[i];
            count++;
        }

        addMirrorPair('b', 'd');
        addMirrorPair('p', 'q');
    }

    // 3. Method to expand around center for palindrome
    // This method assumes that left already = right and stops when symmetry stops
    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 4. Method to find longest palindrome substring
    public static String getLongestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return "-";

        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i); // odd
            int len2 = expand(s, i, i + 1); // even
            int len = Math.max(len1, len2);

            if (len > longest.length()) {
                int start = i - (len - 1) / 2; // racecar
                longest = s.substring(start, start + len);
            }
        }
        return longest.length() >= 2 ? longest : "-";
    }

    // 5. Method to check if a string is mirrorable
    public static boolean isMirrorable(String s) {
        int n = s.length();

        for (int i = 0; i <= n / 2; i++) {
            char left = s.charAt(i);
            char right = s.charAt(n - 1 - i);

            boolean found = false;
            for (int k = 0; k < count; k++) {
                if (original[k] == left && mirror[k] == right) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

    // 6. Method to find longest mirrorable substring
    public static String getLongestMirrorWord(String s) {
        if (s == null || s.length() < 2)
            return "-";

        String longest = "-";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (sub.length() >= 2 && isMirrorable(sub)
                        && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }

    // Main method
    public static void main(String[] args) {
        initDefaultPairs();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter up to 10 mirror pairs (e.g. b d). Press Enter to stop.");
        for (int i = 0; i < 10; i++) {
            System.out.print("Pair " + (i + 1) + ": ");
            String line = sc.nextLine();
            if (line.isEmpty())
                break;

            String[] p = line.split("\\s+");
            if (p.length == 2 && p[0].length() == 1 && p[1].length() == 1) {
                addMirrorPair(p[0].charAt(0), p[1].charAt(0));
            } else {
                System.out.println("Invalid input.");
                i--;
            }
        }

        System.out.print("\nEnter a word: ");
        String word = sc.nextLine();
        sc.close();

        System.out.println("Longest palindrome substring: " + getLongestPalindrome(word));
        System.out.println("Longest mirrorable substring: " + getLongestMirrorWord(word));
    }
}
