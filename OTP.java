import java.util.*;

public class OTP {

    public static String encrypt(char[] a, String message, String key) {
        if (message.length() != key.length()) {
            System.out.println("Key length is not equal to message length");
            return "";
        }

        String cipher = "";

        for (int i = 0; i < message.length(); i++) {
            char msgChar = message.charAt(i);

            for (int j = 0; j < a.length; j++) {
                if (a[j] == msgChar) {
                    char keyChar = key.charAt(i);

                    for (int n = 0; n < a.length; n++) {
                        if (a[n] == keyChar) {
                            cipher += a[(j + n) % 26];
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return cipher;
    }

    public static String decrypt(char[] a, String cipher, String key) {
        if (cipher.length() != key.length()) {
            System.out.println("Key length is not equal to cipher length");
            return "";
        }

        String plainText = "";

        for (int i = 0; i < cipher.length(); i++) {
            char cipherChar = cipher.charAt(i);
            for (int j = 0; j < a.length; j++) {
                if (a[j] == cipherChar) {
                    char keyChar = key.charAt(i);
                    for (int n = 0; n < a.length; n++) {
                        if (a[n] == keyChar) {
                            if ((j - n) < 0) {
                                plainText += a[(j - n + 26) % 26];
                            } else {
                                plainText += a[(j - n) % 26];
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return plainText;
    }

    public static void main(String[] args) {

        char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter message : ");
        String message = sc.nextLine();

        System.out.println("Enter key : ");
        String key = sc.nextLine();
        sc.close();
        message = message.toUpperCase();
        key = key.toUpperCase();

        String cipher = encrypt(alphabet, message, key);

        String plainText = decrypt(alphabet, cipher, key);

        System.out.println("Encryptted : " + cipher);
        System.out.println("Dcrypted : " + plainText);
    }

}