package tp3;

import java.math.BigInteger;
import java.util.Random;

public class tp3 {

    public static BigInteger p, q, n, phiN, e, d; 

    public static void keyGen() {
        long before = System.currentTimeMillis();
        p = BigInteger.probablePrime(10, new Random());
        System.out.println("p: " + p);
		q = BigInteger.probablePrime(10, new Random());
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


    public static BigInteger algoA(BigInteger n, BigInteger d) {
        for (long i = 1; i < 4; i++) {
            BigInteger phi = (d.multiply(BigInteger.valueOf((long) 3)).subtract(BigInteger.ONE)).divide(BigInteger.valueOf(i));
            BigInteger s = n.subtract(phi).add(BigInteger.ONE);
            BigInteger p2 = (s.add((s.pow(2).subtract(n.multiply(BigInteger.valueOf((long) 4)))).sqrt())).divide(BigInteger.TWO);
            System.out.println("p2 : " + p2);
            System.out.println("mod: "+ n.mod(p2));
        
            if (n.mod(p2).equals(BigInteger.ZERO)) {
                return p2;
            }
        }
        return null;
    }

public static void main(String[] args) {
    // Exo 81.
    // λ représente la difficulté.

    // Exo 82.
    // 2) Si on nous donne n et que grâce à a A() nous trouvons p, nous pouvons alors en déduire q.
    // Avec p et q nous pouvons alors retrouver la clé e.
    // 3)
    // 4)
    keyGen();
    System.out.println("alg:" + algoA(n, d));

}

}