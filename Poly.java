import java.util.*;

public class Poly {

    public static String generateKey(String message, String key) {
        int x = message.length();

        for (int i = 0;; i++) {
            if (x == i)
                i = 0;
            if (key.length() == x)
                break;
            key += key.charAt(i);
        }
        return key;
    }

    public static String encrypt(String message, String key) {
        if (message.length() != key.length()) {
            System.out.println("Message and key lengths are not equal");
            return "";
        }

        String cipherText = "";
        for (int i = 0; i < message.length(); i++) {
            int x = (message.charAt(i) + key.charAt(i)) % 26;
            x += 'A';
            cipherText += (char) x;
        }
        return cipherText;
    }

    public static String decrypt(String cipher, String key) {
        if (cipher.length() != key.length()) {
            System.out.println("Cipher length and key length are not equal");
            return "";
        }

        String plainText = "";
        for (int i = 0; i < cipher.length(); i++) {
            int x = (cipher.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A';
            plainText += (char) x;
        }
        return plainText;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message : ");
        String message = sc.nextLine();
        System.out.println("Enter key : ");
        String initKey = sc.nextLine();
        sc.close();
        message = message.toUpperCase();
        initKey = initKey.toUpperCase();

        String key = generateKey(message, initKey);
        String cipher = encrypt(message, key);
        String plainText = decrypt(cipher, key);

        System.out.println("Original message : " + message);
        System.out.println("Key : " + key);
        System.out.println("Encrypt : " + cipher);
        System.out.println("Decrypt : " + plainText);
    }
}