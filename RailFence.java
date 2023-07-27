import java.util.*;

public class RailFence {

    public static char[][] fillTheMatrixWithChar(int depth, String message, char c) {

        char[][] matrix = new char[depth][message.length()];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < message.length(); j++) {
                matrix[i][j] = c;
            }
        }
        return matrix;
    }

    public static String encrypt(int depth, String message) {

        char[][] matrix = fillTheMatrixWithChar(depth, message, '\n');

        // add the plain text in zig zag manner to the matrix
        boolean dirDown = false;
        int row = 0;
        int col = 0;

        for (int i = 0; i < message.length(); i++) {

            if (row == 0 || row == (depth - 1)) {
                dirDown = !dirDown;
            }

            matrix[row][col++] = message.charAt(i);

            if (dirDown) {
                row++;
            } else {
                row--;
            }
        }

        String cipher = "";

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (matrix[i][j] != '\n') {
                    cipher += matrix[i][j];
                }
            }
        }

        return cipher;
    }

    public static String dcrypt(int depth, String cipher) {

        char[][] matrix = fillTheMatrixWithChar(depth, cipher, '\n');

        // find the positions where we can put cipher chars
        boolean dirDown = true;
        int row = 0;
        int col = 0;

        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0) {
                dirDown = true;
            }
            if (row == (depth - 1)) {
                dirDown = false;
            }

            matrix[row][col++] = '*';

            if (dirDown) {
                row++;
            } else {
                row--;
            }
        }

        // now fill the matrix in row wise with cipher text
        int currentChar = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (matrix[i][j] == '*') {
                    matrix[i][j] = cipher.charAt(currentChar++);
                }
            }
        }

        // now read the plain text in zig zag manner
        dirDown = true;
        row = 0;
        col = 0;

        String plain = "";
        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0) {
                dirDown = true;
            }
            if (row == (depth - 1)) {
                dirDown = false;
            }

            plain += matrix[row][col++];

            if (dirDown) {
                row++;
            } else {
                row--;
            }

        }

        return plain;

    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter message : ");
            String message = sc.nextLine();

            System.out.println("Enter depth : ");
            int depth = sc.nextInt();

            String cipher = encrypt(depth, message);

            System.out.println("Cipher : " + cipher);

            String plain = dcrypt(depth, cipher);

            System.out.println("Plain : " + plain);
        }
    }
}