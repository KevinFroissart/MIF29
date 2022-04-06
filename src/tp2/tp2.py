##############################################################################

#Compléter le code
#Debugger
#Tester les propriétés d'homomorphie de Paillier

##############################################################################

from sympy import * 
from random import *

def getprime(k):
	p = randprime(2**(k-1), 2**k)
	return p


def genkeys(k):
        p = getprime(k)
        q = getprime(k)
        while (p==q):
            q = getprime(k)

        N = int(p * q)
        Phi = int( N - p - q +1)
        return [N, mod_inverse(N, Phi)]

# pk={n=pq} ; sk = {p=n-1 mod phi(n)}
def encrypt(m, pk):
    r = randrange(pk-1)
    N2 = pk*pk
    c= ((1+m*pk) * pow(r, pk, N2)) % N2
    return int(c)

def decrypt(c, pk, sk):
    N2 = pk*pk
    r = pow(c, sk, pk)
    s = mod_inverse(r, pk)
    m = ((c * pow(s, pk, N2)) % N2 - 1)//pk
    return int(m)


################ Tests fonctions de chiffrement et déchiffrement ######################

pk, sk = genkeys(300)
print("pk = ", pk)
print("sk = ",sk)
print()


x=2
X = encrypt(x,pk)
dx=decrypt(X,pk,sk)

print('Decrypt ( Encrypt (',x,')) = ',dx)


################ Tests des propriétés d'homomorphie ####################################


def oplus(X,Y,pk):
    return (X * Y) % (pk*pk)

def produitParConstante(X,y,pk):
    return pow(X, y, pk*pk)

def oppose(X,pk):
    return mod_inverse(X, pk*pk)

y=1000
Y=encrypt(y,pk)

Z1=oplus(X,Y,pk)    
Z2=produitParConstante(X,y,pk)
Z3=oppose(Y,pk)

print(decrypt(Z1,pk,sk),decrypt(Z2,pk,sk),decrypt(Z3,pk,sk) - pk)