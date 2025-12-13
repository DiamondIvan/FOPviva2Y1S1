import java.util.Scanner;

public class VivaQ1 {

    // 1. Method to count characters in a string
    public static int countChar(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            count++;
        }
        return count;
    }

    // 2. Method to check if two words are anagrams
    public static boolean isAnagram(String a, String b) {
        a = a.toLowerCase(); 
        b = b.toLowerCase(); 


        boolean[] lettersA = new boolean[26];
        boolean[] lettersB = new boolean[26];

        for (int i = 0; i < a.length(); i++)
            lettersA[a.charAt(i) - 'a'] = true;
        for (int i = 0; i < b.length(); i++)
            lettersB[b.charAt(i) - 'a'] = true;

        for (int i = 0; i < 26; i++)
            if (lettersA[i] != lettersB[i])
                return false;

        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of words: ");
        int n = sc.nextInt();
        String[] words = new String[n];
        int size = 0;

        for (int i = 0; i < words.length; i++) {
            System.out.print("Enter word " + (i + 1) + ": ");
            String w = sc.next().toLowerCase();
            boolean duplicate = false;

            for (int j = 0; j < size; j++) {
                if (words[j].equals(w)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                words[size] = w;
                size++;
            }
        }

        boolean[] grouped = new boolean[size];
        int groupNum = 1;

        for (int i = 0; i < size; i++) {

            boolean found = false;

            for (int j = i + 1; j < size; j++) {
                if (isAnagram(words[i], words[j])) {
                    if (!found) {
                        System.out.print("Anagram group " + groupNum + ": " + words[i] + " ");
                        found = true;
                    }
                    System.out.print(words[j] + " ");
                    grouped[j] = true;
                }
            }

            if (found) { 
                grouped[i] = true;
                System.out.println();
                groupNum++;
            }
        }

        // Display words without anagram group
        System.out.print("Without anagram group: ");
        for (int i = 0; i < size; i++) {
            if (!grouped[i]) {
                System.out.print(words[i] + " ");
            }
        }
        System.out.println();
    }
}
