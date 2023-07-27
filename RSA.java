import java.math.*;


public class RSA {
    public static double gcd(double a, double h) {
       
        double temp;
        while (true) {
            temp = a % h;
            if (temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    public static void main(String[] args) {
        double p = 2;
        double q = 7;
        // Message to be encrypted
        double msg = 5 ;

        // Stores the first part of public key:
        double n = p * q;

        // Finding the other part of public key.
        // double e stands for encrypt

        double phi = (p - 1) * (q - 1);
		double e = 2;
        while (e < phi) {
            /*
             * e must be co-prime to phi and
             * smaller than phi.
             */
            if (gcd(e, phi) == 1)
                break;
            else
                e++;
        }
        System.out.println("e value = " + e);

        double d = 0;
        for(int i=0;i<=100;i++)
		{
			double x=1+(i*phi);
			if(x%e==0)      //d is for private key exponent
			{
				d=x/e;
				break;
			}
		}
        System.out.println("d value = " + d);
        

        System.out.println("Message data = " + msg);

        // Encryption c = (msg ^ e) % n
        double c = Math.pow(msg, e);
        c = c % n;
        System.out.println("Encrypted data = " + c);

        // Decryption m = (c ^ d) % n
        double m = Math.pow(c, d);
        m = m % n;
        System.out.println("Original Message Sent = " + m);
    }
}

