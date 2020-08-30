import java.math.BigInteger;

/**
 * Simplest and most forward implementation which should be the slowest.
 * 
 */
public class SimpleFibonacci implements Fibonacci {

	public BigInteger fib(BigInteger n) {
		if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
			return n;
		} else {
			BigInteger leftFib = fib(n.subtract(BigInteger.TWO));
			BigInteger rightFib = fib(n.subtract(BigInteger.ONE));

			BigInteger fibSum = leftFib.add(rightFib);

			return fibSum;
		}
	}

}
