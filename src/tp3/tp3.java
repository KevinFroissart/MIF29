package tp3;

import java.math.BigInteger;
import java.util.Random;

public class tp3 {

    public static BigInteger p, q, n, phiN, e, d; 

    public static void keyGen() {
        long before = System.currentTimeMillis();
        p = BigInteger.probablePrime(2048, new Random());
        System.out.println("p: " + p);
		q = BigInteger.probablePrime(2048, new Random());
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
        /** Exo 81.
         * 
         * λ représente le paramètre de sécurité. C'est la taille de p et q.
         */

        /** Exo 82.
         * 1) Lignes 33 à 46.
         * 2) Retrouver sk à partir de pk est au moins aussi difficile que de factoriser n. (réduction)
         * 3) Au lieu de faire i de 1 à 3, il faut faire i de 1 à e-1.
         * 4) Si e est petit, du moins inférieur a un polynome(λ)
         */

        keyGen();
        System.out.println("alg:" + algoA(n, d));
        
        /** Exo 83.
         * 
         * 1) Si un homme du milieu écoute les encryptions, il peu alors alors encrypter les numéros de 1 à 49 et comparer les hashs pour trouver la bonne combinaison.
         * 2) Dans le pire cas l'attaquant dois effectuer 10068347520 encryptions soit 10^7 secondes soit 115 jours.
         *    Dans le meilleur cas, 720 encryptions, soit 720ms.
         *    En moyenne il faudrait 58 jours.
         * 3) Si les numéros sont dans l'ordres, il n'y a plus que 6! clés possible (ie. 3.1.2)
         *    115/6!*24 = 4 heures.
         * 4) e = 17 n'est pas judicieux car si c^e > n (c le chiffré de m) alors on peux trouver l'inverse avec
         *    c^e / c^(e-1) = m.
         * 5) On peux ajouter de l'aléatoire.
         */

        /** Exo 84.
         * 
         * Ecryptons m0 et m1 en M0 et M1.
         * m1-b = m0 + m1 - mb soit M' = M0 x M1 x M^-1 mod n²
         */

        /** Exo 85.
         * 
         * Alice génère pka, ska, ra et l'encrypte pour avoir Ra et envoie (pka,Ra) à Bob 
         * Bob choisi rb et l'envoie à Alice
         * Alice envoie ra à Bob
         * Bob vérifie que l'encryption de ra est bien égale à Ra grâce à pka.
         */

        /** Exo 86.
         *  
         * 1) - Chaque étudiant envoie sa note encrypté avec pk à une même personne.
         *    - Cette personne fait la somme des encryption et l'envoie au professeur.
         *    - Le professeur décrypte la somme et calcule la moyenne.
         * 2) Les étudiants peuvent mettre des notes non comprises entre 0 et 10.
         *    Le professeur peux donner une fausse moyenne.
         * 3) On peux vérifier que la moyenne est bien égale à (c*r^-n mod n²-1)/n
         */
    }

}