import java.util.Random;
import java.util.Scanner;

public class VivaQ6 {
    public static boolean moved = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[4][4];

        System.out.println("Enter 4x4 grid:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter move (LEFT/RIGHT/UP/DOWN): ");
        String move = sc.next().toUpperCase();

        moved = false;

        switch (move) {
            case "LEFT":
                moveLeft(grid);
                break;
            case "RIGHT":
                moveRight(grid);
                break;
            case "UP":
                moveUp(grid);
                break;
            case "DOWN":
                moveDown(grid);
                break;
            default:
                System.out.println("Invalid move!");
                return;
        }

        if (moved) {
            addRandomTile(grid);
        }
        System.out.println();
        System.out.println("Updated Grid:");
        printGrid(grid);

        sc.close();
    }

    public static void moveLeft(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            int[] row = new int[4];
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != 0) {
                    row[index++] = grid[i][j];
                }
            }
            for (int j = 0; j < 3; j++) {
                if (row[j] == row[j + 1] && row[j] != 0) {
                    row[j] *= 2;
                    row[j + 1] = 0;
                    moved = true;
                }
            }
            int[] newRow = new int[4];
            index = 0;
            for (int j = 0; j < 4; j++) {
                if (row[j] != 0) {
                    newRow[index++] = row[j];
                }
            }

            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != newRow[j]) {
                    moved = true;
                }
                grid[i][j] = newRow[j];
            }
        }
    }

    public static void moveRight(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            int[] row = new int[4];
            int index = 3;
            for (int j = 3; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    row[index--] = grid[i][j];
                }
            }
            for (int j = 3; j > 0; j--) {
                if (row[j] == row[j - 1] && row[j] != 0) {
                    row[j] *= 2;
                    row[j - 1] = 0;
                    moved = true;
                }
            }
            int[] newRow = new int[4];
            index = 3;
            for (int j = 3; j >= 0; j--) {
                if (row[j] != 0) {
                    newRow[index--] = row[j];
                }
            }

            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != newRow[j]) {
                    moved = true;
                }
                grid[i][j] = newRow[j];
            }
        }
    }

    public static void moveUp(int[][] grid) {
        for (int col = 0; col < 4; col++) {
            int[] b = new int[4];
            int index = 0;
            for (int row = 0; row < 4; row++) {
                if (grid[row][col] != 0) {
                    b[index++] = grid[row][col];
                }
            }

            for (int row = 0; row < 3; row++) {
                if (b[row] == b[row + 1] && b[row] != 0) {
                    b[row] *= 2;
                    b[row + 1] = 0;
                    moved = true;
                }
            }
            int[] newCol = new int[4];
            index = 0;
            for (int row = 0; row < 4; row++) {
                if (b[row] != 0) {
                    newCol[index++] = b[row];
                }
            }
            for (int row = 0; row < 4; row++) {
                if (grid[row][col] != newCol[row]) {
                    moved = true;
                }
                grid[row][col] = newCol[row];
            }
        }
    }

    public static void moveDown(int[][] grid) {
        for (int col = 0; col < 4; col++) {
            int[] a = new int[4];
            int index = 3;
            for (int row = 3; row >= 0; row--) {
                if (grid[row][col] != 0) {
                    a[index--] = grid[row][col];
                }
            }
            for (int row = 3; row > 0; row--) {
                if (a[row] == a[row - 1] && a[row] != 0) {
                    a[row] *= 2;
                    a[row - 1] = 0;
                    moved = true;
                }
            }
            int[] newCol = new int[4];
            index = 3;
            for (int row = 3; row >= 0; row--) {
                if (a[row] != 0) {
                    newCol[index--] = a[row];
                }
            }
            for (int row = 0; row < 4; row++) {
                if (grid[row][col] != newCol[row])
                    moved = true;
                grid[row][col] = newCol[row];
            }
        }
    }

    public static int[][] addRandomTile(int[][] grid) {
        Random rand = new Random();
        while (true) {
            int r = rand.nextInt(4);
            int c = rand.nextInt(4);
            if (grid[r][c] == 0) {
                grid[r][c] = (rand.nextInt(10) < 9) ? 2 : 4;
                return grid;
            }
        }

    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            int[] row = grid[i];
            for (int j = 0; j < 4; j++) {
                int val = row[j];
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
}
