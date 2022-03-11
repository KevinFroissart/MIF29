package tp1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tp1 {

	/**
	 * Permet de calculer une moyenne de temps d'éxecution d'opérations sur des objets de type {@link BigInteger}
	 *
	 * Nécessite JAVA 17 !
	 *
	 * @param nombreIteration le nombre d'itération à faire
	 * @param a le premier objet nécessaire à l'opération
	 * @param b le second objet nécessaire à l'opération
	 * @param typeCalcul l'opération en question
	 * @param uniteAttendue l'unité de temps attendue en sortie
	 * @return la moyenne de temps d'éxecution
	 */
	public static double calculate(int nombreIteration, BigInteger a, BigInteger b, String typeCalcul, String uniteAttendue) {
		long before;
		List<Long> results = new ArrayList<>();

		switch (typeCalcul) {
			case "addition" -> {
				for (int i = 0; i < nombreIteration; i++) {
					before = System.nanoTime();
					a.add(b);
					results.add(System.nanoTime() - before);
				}
			}
			case "multiplication" -> {
				for (int i = 0; i < nombreIteration; i++) {
					before = System.nanoTime();
					a.multiply(b);
					results.add(System.nanoTime() - before);
				}
			}
			case "division" -> {
				for (int i = 0; i < nombreIteration; i++) {
					before = System.nanoTime();
					a.divide(b);
					results.add(System.nanoTime() - before);
				}
			}
			case "modulo" -> {
				for (int i = 0; i < nombreIteration; i++) {
					before = System.nanoTime();
					a.mod(b);
					results.add(System.nanoTime() - before);
				}
			}
		}

		double average = results.stream()
				.mapToDouble(d -> d)
				.average()
				.orElse(0.0);

		return switch (uniteAttendue) {
			case "micro" -> average / 1000;
			case "milli" -> average / 1000 / 1000;
			case "seconds" -> average / 1000 / 1000 / 1000;
			default -> average;
		};
	}

	public static BigInteger aleaInf(BigInteger p) {
		while (true) {
			BigInteger r = new BigInteger(p.bitLength(), new Random());
			if (r.compareTo(BigInteger.ZERO) < 1) continue;
			if (r.compareTo(p) < 0) return r;
		}
	}

	public static void exercice73() {
		BigInteger a, b;

		// ----- Exercice 73
		System.out.println("Exercice 73");
		// ----- 73.1
		System.out.println("1.");
		a = new BigInteger(2048, new Random());
		b = new BigInteger(2048, new Random());
		System.out.println("a : " + a);
		System.out.println("b : " + b);

		// ----- 73.2
		System.out.println("\n2.");
		System.out.println("Taille de a + b = " + a.add(b).bitLength());
		System.out.println("Taille de a * b = " + a.multiply(b).bitLength());

		// ----- 73.3
		System.out.println("\n3.");
		System.out.println("Moyenne en nano secondes (ns) pour 10 000 opérations arithmétiques :");
		System.out.println("Temps écoulé pour a + b = " + tp1.calculate(10000, a, b, "addition", "nano") + "ns");
		System.out.println("Temps écoulé pour a * b = " + tp1.calculate(10000, a, b, "multiplication", "nano") + "ns");
		System.out.println("Temps écoulé pour a / b = " + tp1.calculate(10000, a, b, "division", "nano") + "ns");
		System.out.println("Temps écoulé pour a % b = " + tp1.calculate(10000, a, b, "modulo", "nano") + "ns");
	}

	public static void exercice74() {
		long before, after;
		BigInteger a, b, c;

		// ----- Exercice 74
		System.out.println("\nExercice 74");
		// ----- 74.1
		System.out.println("1.");
		a = new BigInteger(30, new Random());
		b = new BigInteger(30, new Random());
		c = new BigInteger(30, new Random());
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("c : " + c);

		// ----- 74.2.a
		System.out.println("\n2.a");
//		before = System.currentTimeMillis();
//		a.pow(b.intValue()).mod(c);
//		after = System.currentTimeMillis();
//		System.out.println("time: " + (after - before));
		System.out.println("Nous ne pouvons pas faire c = a^(p−1) puis c mod p! Stack overflow");

		// ----- 74.2.b
		System.out.println("\n2.b");
		before = System.nanoTime() / 1000;
		a.modPow(b, c);
		after = System.nanoTime() / 1000;
		System.out.println("Temps écoulé pour 30 bits en utilisant modPow: " + (after - before) + "µs");

		// ----- 74.3
		System.out.println("\n3.");
		a = new BigInteger(2048, new Random());
		b = new BigInteger(2048, new Random());
		c = new BigInteger(2048, new Random());

		before = System.nanoTime() / 1000;
		a.modPow(b, c);
		after = System.nanoTime() / 1000;
		System.out.println("Temps écoulé pour 2048 bits en utilisant modPow: " + (after - before) + "µs, soit ~" + ((after - before) / 1000.0) + "ms");
	}

	public static void exercice75(int nbTest) {
		BigInteger a, p;
		int aleaInfFailCpt;

		// ----- Exercice 75
		System.out.println("\nExercice 75");
		// ----- 75.1
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");
		aleaInfFailCpt = 0;

		System.out.println("Itération sur " + nbTest + " tests");
		System.out.println("Calcul en cours.....");

		for (int i = 0; i < nbTest; i++) {
			p = BigInteger.probablePrime(2048, new Random());
			// ----- 75.2
			a = aleaInf(p);
			// ----- 75.3
			if (!a.modPow(p, p).equals(a)) aleaInfFailCpt++;
		}

		System.out.println("a^p mod p = a : vrai " + (nbTest - aleaInfFailCpt) + "/" + nbTest + ", faux " + aleaInfFailCpt + "/" + nbTest);
	}

	public static void exercice76(int nbTest) {
		BigInteger a, p, q, n, phiN;
		int aleaInfFailCpt;

		// ----- Exercice 76
		System.out.println("\nExercice 76");
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");

		System.out.println("Itération sur " + nbTest + " tests");
		System.out.println("Calcul en cours.....");
		aleaInfFailCpt = 0;

		for (int i = 0; i < nbTest; i++) {
			// ----- 76.1
			p = BigInteger.probablePrime(1024, new Random());
			q = BigInteger.probablePrime(1024, new Random());
			n = p.multiply(q);

			// ----- 76.2
			phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

			// ----- 76.3
			a = aleaInf(p);

			// ----- 76.4
			if (!a.modPow(phiN, n).equals(BigInteger.ONE)) aleaInfFailCpt++;
		}

		System.out.println("a^phiN mod n = 1 : vrai " + (nbTest - aleaInfFailCpt) + "/" + nbTest + ", faux " + aleaInfFailCpt + "/" + nbTest);
	}

	public static void exercice77(int nbTest) {
		BigInteger a, b, p, q, n;
		int aleaInfFailCpt;

		// ----- Exercice 77
		System.out.println("\nExercice 77");
		// ----- 77.1
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");

		System.out.println("Itération sur " + nbTest + " tests");
		System.out.println("Calcul en cours.....");
		aleaInfFailCpt = 0;

		for (int i = 0; i < 50; i++) {
			// ----- 77.1
			p = BigInteger.probablePrime(1024, new Random());
			q = BigInteger.probablePrime(1024, new Random());
			n = p.multiply(q);

			// ----- 77.2
			a = aleaInf(p);
			b = a.modInverse(n);

			// ----- 77.3
			if (!a.multiply(b).mod(n).equals(BigInteger.ONE)) aleaInfFailCpt++;
		}

		System.out.println("(a * b) mod n = 1 : vrai " + (nbTest - aleaInfFailCpt) + "/" + nbTest + ", faux " + aleaInfFailCpt + "/" + nbTest);

		// ----- 77.4
		System.out.println("\n4.");
		System.out.println("Nous ne pouvons pas trouver l'inverse de p%n car p est un nombre premier et n'est donc pas inversible");
	}

	public static void exercice78() {
		// ----- Exercice 78
		System.out.println("\nExercice 78");
		// ----- 78.1
		System.out.println("\n1.");
		System.out.println("Pour l'algorithme A: ");
		System.out.println("Prenons un Biginteger.probablePrime x avec une taille comprise entre [p.bitLength - 1, p.bitLength + 1]");
		System.out.println("Ensuite divisons n par x ce qui donne y, multiplier y et x, si le résultat est égal à n");
		System.out.println("nous avons alors trouvé nos p et q.");
		System.out.println("Cet algorithme n'est pas rapide et ne peux pas être utilisé sur 2048 bits.");

		// ----- 78.2
		System.out.println("\n2.");
		System.out.println("Pour l'algorithme B: ");
		System.out.println("Dans l'opération S ← n − φ(n) + 1, nous avons n qui vaut n bits, phi(n) qui au pire vaut 1 bit et du coup S qui au pire vaut n bits");
		System.out.println("Dans l'opéraion (S + √S² − 4n)/2, S² − 4n reviens à n bits car, S² = 2n bits, 4n = n+2 bits");
		System.out.println("suivi de racine(n bits) qui au pire vaut n bits. S + racine(n bits) est au pire égal à 2n bits");
		System.out.println("donc 2n bits / 2 reviens à n bits, dans le pire des cas.");
		System.out.println("Pour l'autre partie du calcul, c'est pratiquement la même chose");
		System.out.println("le point étant que, l'opération la plus longue étant faite lors du calcul de la racine suivi de la division à 2n bits");
		System.out.println("pour renvoyer au final des chiffres de n bits long, le calcul est vraisemblablement rapide.");

		// ----- 78.3
		System.out.println("\n3.");
		System.out.println("Nous ne pouvons pas trouver phi(n) rapidement avec A' car si nous pouvions le faire, nous");
		System.out.println("pourrions trouver p et q rapidement, or nous savons que cette opération n'est pas rapide avec l'algorithme A.");
		System.out.println("Nous ne pouvons donc pas trouver phi(n) rapidement avec A'.");
	}

	public static void main(String args[]) {
		long before = System.currentTimeMillis();
		exercice73();
		exercice74();
		exercice75(50);
		exercice76(100);
		exercice77(100);
		exercice78();
		System.out.println("-----------");
		System.out.println("Temps total d'éxecution du programme : " + (System.currentTimeMillis() - before) / 1000.0  + "s");
	}
}
