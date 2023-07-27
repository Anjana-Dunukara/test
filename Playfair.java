import java.util.*;

public class Playfair {

    static String removeDuplicateElemetsFromKey(String key) {
        if ("" != key && null != key) {
            char array[] = new char[key.length()];
            for (int i = 0; i < array.length; i++) {
                if (isNotContains(array, key.charAt(i))) {
                    array[i] = key.charAt(i);
                }
            }
            return removeEmptyElements(array);
        }
        return "";
    }

    static boolean isNotContains(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return false;
            }
        }
        return true;
    }

    static String removeEmptyElements(char[] array) {
        String arrayString = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i] != '\u0000') {
                arrayString += array[i];
            }
        }
        return arrayString;
    }

    static String createKeyAndAlpabetArray(char[] alpabet, String key) {
        char array[] = new char[25];
        for (int i = 0; i < key.length(); i++) {
            array[i] = key.charAt(i);
        }
        int count = key.length();
        for (int i = 0; i < array.length; i++) {

            if (isNotContains(array, alpabet[i])) {
                array[count] = alpabet[i];

                count++;
            }
        }
        return removeEmptyElements(array);
    }

    static char[][] createKeyMatrix(String keyString) {
        char matrix[][] = new char[5][5];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyString.charAt(k);
                k++;
            }
        }
        return matrix;
    }

    static void printKeyMatixform(char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }

    static String toString(char[] ch) {
        String result = "";
        for (int i = 0; i < ch.length; i++) {
            result += ch[i];
        }
        return result;
    }

    static int arrayLength(String str) {
        // Calculating the length of the array that store plaintext after grouped as
        // pairs
        int q = 0;
        int y = 0;
        while (str.charAt(q) != ' ') {
            if (str.charAt(q) == str.charAt(q + 1)) {
                q++;
                y = y + 2;
            } else {
                if (str.charAt(q + 1) == ' ') {
                    y = y + 2;
                    q++;
                } else {
                    y = y + 2;
                    q = q + 2;
                }
            }
        }
        return y;
    }

    static char[] breakingTheStringAsPairs(String str) {
        // str.length();
        char textArr[] = new char[arrayLength(str)];
        int q = 0;
        int y = 0;
        // Breaking plaintext as pairs
        while (y != textArr.length) {
            if (str.charAt(q) == str.charAt(q + 1)) {
                textArr[y] = str.charAt(q);
                textArr[y + 1] = 'X';
                q++;
                y = y + 2;
            } else {
                if (str.charAt(q + 1) == ' ') {
                    textArr[y] = str.charAt(q);
                    textArr[y + 1] = 'X';
                    y = y + 2;
                    q++;
                } else {
                    textArr[y] = str.charAt(q);
                    textArr[y + 1] = str.charAt(q + 1);
                    y = y + 2;
                    q = q + 2;
                }
            }
        }
        return textArr;
    }

    static int rowIndex(char matrix[][], char letter) {
        int row = 0;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (matrix[r][c] == letter) {
                    row = r;
                }
            }
        }
        return row;
    }

    static int columnIndex(char matrix[][], char letter) {
        int column = 0;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (matrix[r][c] == letter) {
                    column = c;
                }
            }
        }
        return column;
    }

    static String encryption(String str, String key, char[][] matrix) {

        char[] textArr = breakingTheStringAsPairs(str);
        char[] cipher = new char[arrayLength(str)];

        // Encrypting the plaintext using the matrix that stored the keyword and other
        // alphabetical letters

        char letter1, letter2;
        int p = 0;

        int r1 = 0;
        int r2 = 0;

        int c1 = 0;
        int c2 = 0;

        int index = 0;

        for (int i = 0; i < cipher.length / 2; i++) {
            letter1 = textArr[p];
            p++;

            r1 = rowIndex(matrix, letter1);
            c1 = columnIndex(matrix, letter1);

            letter2 = textArr[p];
            p++;

            r2 = rowIndex(matrix, letter2);
            c2 = columnIndex(matrix, letter2);

            if (r1 == r2) {
                if (c1 == 4) {
                    cipher[index] = matrix[r1][(c1 + 1) % 5];
                    index++;
                } else {
                    cipher[index] = matrix[r1][c1 + 1];
                    index++;
                }

                if (c2 == 4) {
                    cipher[index] = matrix[r2][(c2 + 1) % 5];
                    index++;
                } else {
                    cipher[index] = matrix[r2][c2 + 1];
                    index++;
                }
            }

            else if (c1 == c2) {
                if (r1 == 4) {
                    cipher[index] = matrix[(r1 + 1) % 5][c1];
                    index++;
                } else {
                    cipher[index] = matrix[r1 + 1][c1];
                    index++;
                }

                if (r2 == 4) {
                    cipher[index] = matrix[(r2 + 1) % 5][c2];
                    index++;
                } else {
                    cipher[index] = matrix[r2 + 1][c2];
                    index++;
                }
            }

            else {
                cipher[index] = matrix[r1][c2];
                index++;

                cipher[index] = matrix[r2][c1];
                index++;
            }
        }

        return toString(cipher);
    }

    static char[] toArray(String str) {
        char arr[] = new char[str.length() - 2];
        for (int i = 0; i < str.length() - 2; i++) {
            arr[i] = str.charAt(i);
        }
        return arr;
    }

    static String removeFillerCharacter(char[] arr) {
        String plainText = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 'X') {
                plainText += arr[i];
            }
        }
        return plainText;
    }

    static String decryption(String str, String key, char[][] matrix) {
        // Decrypting the ciphertext
        // Reverse of encryption
        char letter1, letter2;
        int p = 0;

        int r1 = 0;
        int r2 = 0;

        int c1 = 0;
        int c2 = 0;

        int index = 0;

        int n = str.length() - 2;

        char plain[] = new char[n];
        char[] cipher = toArray(str);

        for (int i = 0; i < n / 2; i++) {
            letter1 = cipher[p];
            p++;

            r1 = rowIndex(matrix, letter1);
            c1 = columnIndex(matrix, letter1);

            letter2 = cipher[p];
            p++;

            r2 = rowIndex(matrix, letter2);
            c2 = columnIndex(matrix, letter2);

            if (r1 == r2) {
                if (c1 == 0) {
                    plain[index] = matrix[r1][(c1) + 4];
                    index++;
                } else {
                    plain[index] = matrix[r1][c1 - 1];
                    index++;
                }

                if (c2 == 0) {
                    plain[index] = matrix[r2][(c2) + 4];
                    index++;
                } else {
                    plain[index] = matrix[r2][c2 - 1];
                    index++;
                }

            }

            else if (c1 == c2) {
                if (r1 == 0) {
                    plain[index] = matrix[(r1) + 4][c1];
                    index++;
                } else {
                    plain[index] = matrix[r1 - 1][c1];
                    index++;
                }

                if (r2 == 0) {
                    plain[index] = matrix[(r2) + 4][c2];
                    index++;
                } else {
                    plain[index] = matrix[r2 - 1][c2];
                    index++;
                }
            }

            else {
                plain[index] = matrix[r1][c2];
                index++;

                plain[index] = matrix[r2][c1];
                index++;
            }
        }
        String plainText = removeFillerCharacter(plain);
        return plainText;
    }

    public static void main(String[] args) {

        char alphabet[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the message : ");
            String str1 = sc.nextLine();
            String message = (str1.toUpperCase() + " " + " ");

            System.out.println();

            System.out.print("Enter the key : ");
            String key1 = sc.nextLine();
            String key = key1.toUpperCase();

            String uniqueKeyString = removeDuplicateElemetsFromKey(key);
            System.out.println(uniqueKeyString);

            String uniqueArrayString = createKeyAndAlpabetArray(alphabet, uniqueKeyString);
            System.out.println(uniqueArrayString);

            char[][] keyMatrix = createKeyMatrix(uniqueArrayString);
            printKeyMatixform(keyMatrix);

            System.out.println("Select the option : ");
            System.out.println("	1.Encryption");
            System.out.println("	2.Decryption");

            System.out.print("Option(1/2) : ");
            int option = sc.nextInt();

            String cipherText = "";

            if (option == 1) {
                cipherText = encryption(message, key, keyMatrix);
                System.out.println("cipherText : " + cipherText);
            } else if (option == 2) {
                String OriginalText = decryption(message, key, keyMatrix);
                System.out.println("Original Text : " + OriginalText);
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
