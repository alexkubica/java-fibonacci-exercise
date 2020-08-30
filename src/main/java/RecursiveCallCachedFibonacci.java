import java.math.BigInteger;
import java.util.HashMap;

/**
 * Implementation with caching only recursive calls.
 *
 */
public class RecursiveCallCachedFibonacci implements Fibonacci {

	public HashMap<BigInteger, BigInteger> fibCache;

	public RecursiveCallCachedFibonacci() {
		fibCache = new HashMap<BigInteger, BigInteger>();
		fibCache.put(BigInteger.ZERO, BigInteger.ZERO);
		fibCache.put(BigInteger.ONE, BigInteger.ONE);
	}

	public BigInteger fib(BigInteger n) {
		if (fibCache.containsKey(n)) {
			return fibCache.get(n);
		} else {
			BigInteger leftFib = fib(n.subtract(BigInteger.TWO));
			BigInteger rightFib = fib(n.subtract(BigInteger.ONE));

			BigInteger fibSum = leftFib.add(rightFib);
			fibCache.put(n, fibSum);

			return fibSum;
		}
	}

}
