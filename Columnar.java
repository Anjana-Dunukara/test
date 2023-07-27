import java.util.Scanner;

public class Columnar {

    private static Scanner in;

    private static void encryption() {
        System.out.print("Enter Message: ");
        String msg = in.nextLine();

        System.out.print("Enter Keyword: ");
        String keyword = in.nextLine().toUpperCase();

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(keyword);

        // in case characters don't fit the entire grid perfectly.
        int extraLetters = msg.length() % keyword.length();
        // System.out.println(extraLetters);
        int dummyCharacters = keyword.length() - extraLetters;

        if (extraLetters != 0) {
            for (int i = 0; i < dummyCharacters; i++) {
                msg += "_";
            }
        }

        int numOfRows = msg.length() / keyword.length();

        // Converting message into a grid
        char[][] arr = new char[numOfRows][keyword.length()];

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                arr[i][j] = msg.charAt(z);
                z++;
            }

        }

        // cipher text generation
        String cipherText = "";
        for (int i = 1; i <= keyword.length(); i++) {
            // finding the column
            int column = -1;
            for (int c = 0; c < kywrdNumList.length; c++) {
                if (kywrdNumList[c] == i) {
                    column = c;
                    break;
                }
            }
            // generate cipher text based on the above column
            for (int j = 0; j < numOfRows; j++) {
                cipherText += arr[j][column];
            }
        }

        System.out.println("Cipher Text: " + cipherText);

    } // encryption method

    private static void decryption() {

        System.out.print("Enter Message: ");
        String msg = in.nextLine();

        System.out.print("Enter Keyword: ");
        String keyword = in.nextLine().toUpperCase();

        int numOfRows = msg.length() / keyword.length();

        // array with dummy values
        char[][] arr = new char[numOfRows][keyword.length()];

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(keyword);

        int index = 0;
        for (int i = 0; i < kywrdNumList.length; i++) {
            // finding the colunm
            int colunm = -1;
            for (int c = 0; c < kywrdNumList.length; c++) {
                if (kywrdNumList[c] == i + 1) {
                    colunm = c;
                    break;
                }
            }
            // filling the cipher text letters based on the colunm
            for (int j = 0; j < numOfRows; j++) {
                arr[j][colunm] = msg.charAt(index++);
            }
        }
        // generating the plain text
        String plainText = "";
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                plainText += arr[i][j];
            } // inner for loop
        } // for loop

        // remove _ character
        String finalPlainText = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) != '_') {
                finalPlainText += plainText.charAt(i);
            }
        }
        System.out.println("Plain Text: " + finalPlainText);

    } // decryption function

    private static int[] keywordNumAssign(String keyword) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] kywrdNumList = new int[keyword.length()];

        int init = 0;
        for (int i = 0; i < alpha.length(); i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (alpha.charAt(i) == keyword.charAt(j)) {
                    init++;
                    kywrdNumList[j] = init;
                }
            } // inner for
        } // for
        return kywrdNumList;
    } // keyword number assignment function

    public static void main(String[] args) {
        System.out.println("Columnar Transposition Cipher");
        in = new Scanner(System.in);

        System.out.print("1. Encryption\n2. Decryption\nChoose(1,2): ");
        int choice = in.nextInt();
        in.nextLine();

        if (choice == 1) {
            System.out.println("Encryption");
            encryption();

        } else if (choice == 2) {
            System.out.println("Decryption");
            decryption();

        } else {
            System.out.println("Invalid Choice");
            System.exit(0);
        }
    }

}