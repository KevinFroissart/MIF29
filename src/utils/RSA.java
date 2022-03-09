package utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * see {@link https://gist.github.com/obikag/6385386}
 */
public class RSA {

	static BigInteger p, q, n, phiN, e, d;

	public static BigInteger generateRandomPrime() {
		return new BigInteger(1024, 10, new Random());
	}

	public static void KeyGen() {
		p = generateRandomPrime();
		q = generateRandomPrime();
		n = p.multiply(q);
		phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		e = (BigInteger.ONE).add(BigInteger.ONE);

		while ((e.gcd(phiN)).equals(BigInteger.ONE) == false) {
			e = e.add(BigInteger.ONE);
		}

		d = e.modInverse(phiN);

	}

	public static BigInteger Encrypt(BigInteger n, BigInteger e, BigInteger x) {
		return x.modPow(e, n);
	}

	public static BigInteger Decrypt(BigInteger d, BigInteger c) {
		return c.modPow(d, n);
	}

	public static void main(String[] args) {
		BigInteger x = new BigInteger(10, new Random());
		KeyGen();
		System.out.println("J'ai choisi le nombre : " + x);
		BigInteger c = Encrypt(n, e, x);
		System.out.println("Mon nombre encrypté : " + c);
		System.out.println("Mon nombre décrypté :" + Decrypt(d, c));
	}
}
