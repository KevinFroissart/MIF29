package tp2;

import java.math.BigInteger;
import java.util.Random;

public class tp2 {
    
    public static BigInteger p, q, n, phiN, e, d; 

    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        long before = System.nanoTime() / 1000;
        BigInteger c = message.modPow(e, n);
        System.out.println("Temps écoulé pour l'encryption: " + (System.nanoTime() / 1000 - before) + "µs");
        return c;
    }

    public static BigInteger decrypt(BigInteger messageEncrypte, BigInteger d, BigInteger n) {
        long before = System.nanoTime() / 1000;
        BigInteger m = messageEncrypte.modPow(d, n);
        System.out.println("Temps écoulé pour la décryption: " + (System.nanoTime() / 1000 - before) + "µs");
        return m;
    }
    public static void keyGen() {
        long before = System.currentTimeMillis();
        p = BigInteger.probablePrime(1024, new Random());
		q = BigInteger.probablePrime(1024, new Random());
        // n = modulo utilisé pour les calculs
		n = p.multiply(q);
        // Permet de calculer e et d
		phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        // e pour encrypt, clé d'encryption
        e = BigInteger.TWO;

        while(!e.gcd(phiN).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        // d pour decrypt, clé de decryption
        d = e.modInverse(phiN);
        System.out.println("Temps écoulé pour le KeyGen: " + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        BigInteger nombre = new BigInteger(256, new Random());
        keyGen();
        System.out.println("J'ai choisi le nombre : " + nombre);
		BigInteger nombreEncrypte = encrypt(nombre, e, n);
		System.out.println("Mon nombre encrypté : " + nombreEncrypte);
		System.out.println("Mon nombre décrypté :" + decrypt(nombreEncrypte, d, n));
        System.out.println("Temps totald'éxecution: " + (System.currentTimeMillis() - before) + "ms");
    }
}

/**
 * Q2. e doit être premier avec φ(n).
 * Q3. d et e sont premiers avec phiN qui est pair.
 * Q4. Pour sauver du temps.
 * Q5. Avec ma machine en moyenne, KeyGen = 150ms, Encrypt = 47 µs, Decrypt = 5580 µs
 * Pour 1 Go, il faurdrait plus de 26 minutes.
 * Q6. Car RSA est déterministe et ne peux donc pas être sémantiquement sûr.
 */