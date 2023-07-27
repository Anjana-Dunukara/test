import java.util.*;

public class Mono {

    public static final char plainArray[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    public static final char cipherArray[] = { 'd', 'k', 'v', 'q', 'f', 'i', 'b', 'j', 'w', 'p', 'e', 's', 'c', 'x',
            'h',
            't', 'm', 'y', 'a', 'u', 'o', 'l', 'r', 'g', 'z', 'n' };

    // ENCRYPTION
    public static String encrypt(String message) {
        message = message.toLowerCase();
        String cipherText = "";

        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);

            for (int j = 0; j < plainArray.length; j++) {
                if (plainArray[j] == current) {
                    cipherText += cipherArray[j];
                    break;
                }
            }
        }

        return cipherText.toUpperCase();
    }

    // DCRYPTION
    public static String decrypt(String cipher) {
        cipher = cipher.toLowerCase();
        String plainText = "";

        for (int i = 0; i < cipher.length(); i++) {
            char current = cipher.charAt(i);

            for (int j = 0; j < cipherArray.length; j++) {
                if (cipherArray[j] == current) {
                    plainText += plainArray[j];
                    break;
                }
            }
        }

        return plainText.toUpperCase();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the message : ");
        String message = sc.nextLine();
        sc.close();

        String cipher = encrypt(message);
        String plain = decrypt(cipher);

        System.out.println("Encrypted : " + cipher);
        System.out.println("Decrypted : " + plain);
    }

}