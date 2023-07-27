import java.util.*;

public class Caesar {
    static final String alpha = "abcdefghijklmnopqrstuvwxyz";

    public static String encryption(String message, int key) {
        String encryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLetter(message.charAt(i))) {
                char ch = message.charAt(i);
                int index = alpha.indexOf(ch);
                int newCharIndex = (index + key) % 26;
                encryptedMessage += alpha.charAt(newCharIndex);
            } else {
                encryptedMessage += message.charAt(i);
            }
        }
        return encryptedMessage;
    }

    public static String decryption(String message, int key) {
        String dcryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            int index = alpha.indexOf(message.charAt(i));
            int newIndex = (index - key + 26) % 26;
            dcryptedMessage += alpha.charAt(newIndex);
        }

        return dcryptedMessage;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the message : ");
        String message = sc.nextLine();
        System.out.println("Enter the shift key : ");
        int key = sc.nextInt();
        sc.close();
        message = message.toLowerCase();

        System.out.println("Encrypted :- " + encryption(message, key));
        System.out.println("Dcrypted :- " + decryption(encryption(message, key), key));
    }
}
