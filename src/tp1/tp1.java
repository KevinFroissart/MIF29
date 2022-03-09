package tp1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tp1 {

	public static double calculate(int iter, BigInteger a, BigInteger b, String type, String output) {
		List<Long> results = new ArrayList<>();

		if (type.equals("addition")) {
			for (int i = 0; i < iter; i++) {
				long before = System.currentTimeMillis();
				a.add(b);
				results.add(System.currentTimeMillis() - before);
			}
		}
		else if (type.equals("multiplication")) {
			for (int i = 0; i < iter; i++) {
				long before = System.currentTimeMillis();
				a.multiply(b);
				results.add(System.currentTimeMillis() - before);
			}
		}
		else if (type.equals("division")) {
			for (int i = 0; i < iter; i++) {
				long before = System.currentTimeMillis();
				a.divide(b);
				results.add(System.currentTimeMillis() - before);
			}
		}
		else if (type.equals("modulo")) {
			for (int i = 0; i < iter; i++) {
				long before = System.currentTimeMillis();
				a.mod(b);
				results.add(System.currentTimeMillis() - before);
			}
		}

		double average = results.stream()
				.mapToDouble(d -> d)
				.average()
				.orElse(0.0);

		return switch (output) {
			case "micro" -> average * 1000;
			case "seconds" -> average / 1000;
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

	/**
	 * nécessite JAVA 17
	 */
	public static void main(String args[]) {
		long before, after;
		BigInteger a, b, c, p, q, n, phiN;
		boolean aleaInf;

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
		System.out.println("Temps écoulé pour a + b = " + tp1.calculate(10000, a, b, "addition", "micro") + "µs");
		System.out.println("Temps écoulé pour a * b = " + tp1.calculate(10000, a, b, "multiplication", "micro") + "µs");
		System.out.println("Temps écoulé pour a / b = " + tp1.calculate(10000, a, b, "division", "micro") + "µs");
		System.out.println("Temps écoulé pour a % b = " + tp1.calculate(10000, a, b, "modulo", "micro") + "µs");

		// ----- Exercice 74
		System.out.println("\nExercice 74");
		// ----- 74.1
		System.out.println("1.");
		a = new BigInteger(30, new Random());
		b = new BigInteger(30, new Random());
		c = new BigInteger(30, new Random());

		// ----- 74.2.a
		System.out.println("2.a");
//		before = System.currentTimeMillis();
//		a.pow(b.intValue()).mod(c);
//		after = System.currentTimeMillis();
//		System.out.println("time: " + (after - before));
		System.out.println("On ne peux pas le faire ! Stack overflow");

		// ----- 74.2.b
		System.out.println("\n2.b");
		before = System.currentTimeMillis();
		a.modPow(b, c);
		after = System.currentTimeMillis();
		System.out.println("Temps écoulé pour 30 bits: " + (after - before) + "ms");

		// ----- 74.3
		a = new BigInteger(2048, new Random());
		b = new BigInteger(2048, new Random());
		c = new BigInteger(2048, new Random());

		System.out.println("\n3.");
		before = System.currentTimeMillis();
		a.modPow(b, c);
		after = System.currentTimeMillis();
		System.out.println("Temps écoulé pour 2048 bits: " + (after - before) + "ms");

		// ----- Exercice 75
		System.out.println("\nExercice 75");
		// ----- 75.1
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");
		aleaInf = true;

		System.out.println("Itération sur 30 tests");
		System.out.println("Calcul en cours.....");
		for (int i = 0; i < 30; i++) {
			p = BigInteger.probablePrime(2048, new Random());
			// ----- 75.2
			a = aleaInf(p);
			// ----- 75.3
			if (!a.modPow(p, p).equals(a)) aleaInf = false;
		}
		System.out.println("a^p mod p = a : " + (aleaInf ? "vrai" : "faux"));

		// ----- Exercice 76
		System.out.println("\nExercice 76");
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");

		System.out.println("Itération sur 50 tests");
		System.out.println("Calcul en cours.....");
		aleaInf = true;
		for (int i = 0; i < 50; i++) {
			// ----- 76.1
			p = BigInteger.probablePrime(1024, new Random());
			q = BigInteger.probablePrime(1024, new Random());
			n = p.multiply(q);

			// ----- 76.2
			phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

			// ----- 76.3
			a = aleaInf(p);

			// ----- 76.4
			if (!a.modPow(phiN, n).equals(BigInteger.ONE)) {
				aleaInf = false;
			}
		}
		System.out.println("a^phiN mod n = 1 : " + (aleaInf ? "vrai" : "faux"));

		// ----- Exercice 77
		System.out.println("\nExercice 77");
		// ----- 77.1
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("3.");

		System.out.println("Itération sur 50 tests");
		System.out.println("Calcul en cours.....");
		aleaInf = true;
		for (int i = 0; i < 50; i++) {
			// ----- 77.1
			p = BigInteger.probablePrime(1024, new Random());
			q = BigInteger.probablePrime(1024, new Random());
			n = p.multiply(q);

			// ----- 77.2
			a = aleaInf(p);
			b = a.modInverse(n);

			// ----- 77.3
			if (!a.multiply(b).mod(n).equals(BigInteger.ONE)) {
				aleaInf = false;
			}
		}

		System.out.println("(a * b) mod n = 1 : " + (aleaInf ? "vrai" : "faux"));

		// ----- 77.4
		System.out.println("\n4.");
		System.out.println("On ne peux pas le trouver car p est un nombre premier et n'est donc pas inversible");

		// ----- Exercice 78
		System.out.println("\nExercice 78");
		// ----- 78.1
		System.out.println("\n1.");
		System.out.println("Pour l'algorithme A: ");
		System.out.println("prenons un Biginteger.probablePrime x avec une taille comprise entre [p.bitLength - 1, p.bitLength + 1]");
		System.out.println("diviser n par x ce qui donne y, multiplier y et x, si le résultat est égal à n");
		System.out.println("nous avons trouvé nos p et q.");
		System.out.println("Cet algorithme n'est pas rapide et ne peux pas être utilisé sur 2048 bits.");

		// ----- 78.2
		System.out.println("\n2.");
		System.out.println("Pour l'algorithme B: ");
		System.out.println("Dans l'opération S ← n − φ(n) + 1, nous avons n qui vaut n bits, phi(n) qui au pire vaut 1 bit et du coup S qui au pire vaut n bits");
		System.out.println("(S + √S² − 4n)/2, reviens à n bits encore car, S² = 2n bits, 4n = n+2 bits");
		System.out.println("S² − 4n reviens donc à n bits, suivi de racine(n bits). S + racine(n bits) est au pire égal à 2n bits");
		System.out.println("donc 2n bits / 2 = n bits, dans le pire des cas.");
		System.out.println("Pour l'autre partie du calcul, c'est pratiquement la même chose");
		System.out.println("le point étant que, l'opération la plus longue étant faite lors du calcul de la racine suivi de la division à 2n bits");
		System.out.println("pour renvoyer au final des chiffres de n bits long, le calcul est vraisemblablement rapide.");

		// ----- 78.3
		System.out.println("\n3.");
		System.out.println("On ne peux pas trouver phi(n) rapidement avec A' car si nous pouvions le faire, nous");
		System.out.println("pourrions trouver p et q rapidement or nous savons que cette opération n'est pas rapide avec A.");
		System.out.println("Nous ne pouvons donc pas trouver phi(n) rapidement avec A'.");
	}
}
