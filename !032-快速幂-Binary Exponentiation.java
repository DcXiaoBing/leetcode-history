// https://oi-wiki.org/math/binary-exponentiation/
// a^b
long binpow(long a, long b, long m) {
  a %= m;
  long res = 1;
  while (b > 0) {
    if (b & 1) res = res * a % m;
    a = a * a % m;
    b >>= 1;
  }
  return res;
}

long binpow(long base, long power, long modulo) {
  base %= modulo;
  long res = 1;
  while (power > 0) {
    if ((power & 1) != 0) res = res * base % modulo;
    base = base * base % modulo;
    power >>= 1;
  }
  return res;
}
