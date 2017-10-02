
public class Fraction {
	private int numerator;
	private int denominator;

	/* Constructors */
	public Fraction(int num) {
		this.numerator = num;
		this.denominator = 1;
	}

	public Fraction(int num, int den) {
		if (den == 0) {
			throw new IllegalArgumentException();
		} else if (den < 0) {
			this.numerator = -num;
			this.denominator = -den;
		} else {
			this.numerator = num;
			this.denominator = den;
		}
	}

	/* Methods */
	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	public double toDouble() {
		return numerator / denominator;
	}

	public Fraction add(Fraction other) {
		Fraction result = new Fraction(
				(this.numerator * other.getDenominator()) + (other.getNumerator() * this.denominator),
				denominator * other.getDenominator());
		result.toLowestTerms();
		return result;
	}

	public Fraction subtract(Fraction other) {
		Fraction result = new Fraction(
				(this.numerator * other.getDenominator()) - (other.getNumerator() * this.denominator),
				denominator * other.getDenominator());		result.toLowestTerms();
		return result;
	}

	public Fraction multiply(Fraction other) {
		Fraction result = new Fraction(numerator * other.getNumerator(), denominator * other.getDenominator());
		return result;
	}

	public Fraction divide(Fraction other) {
		if (numerator == 0) {
			throw new IllegalArgumentException();
		}
		Fraction result = new Fraction(other.getNumerator() / numerator, other.getDenominator() / denominator);
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() == Fraction.class) {
			if (this.toDouble() == ((Fraction) other).toDouble()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param a:
	 *            numerator
	 * @param b:
	 *            denominator
	 * @return a: greatest common divisor
	 */
	public static int gcd(int num, int den) {
		/* The Euclidean Algorithm */
		int remainder;
		while (num != 0 && den != 0) {
			remainder = num % den;
			num = den;
			den = remainder;
		}
		return num;
	}

	public void toLowestTerms() {
		int gcd = gcd(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
}
