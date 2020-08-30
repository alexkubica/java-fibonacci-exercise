import java.math.BigInteger;
import java.util.HashMap;

/**
 * Implementation with caching of recursive calls, bit integer's subtract.
 *
 */
public class SubstractAndRecCallCachedFib implements Fibonacci {

	public HashMap<BigInteger, BigInteger> fibCache;
	public HashMap<BigInteger, BigInteger> nMinusOneCache;
	public HashMap<BigInteger, BigInteger> nMinusTwoCache;

	public SubstractAndRecCallCachedFib() {
		fibCache = new HashMap<BigInteger, BigInteger>();
		nMinusOneCache = new HashMap<BigInteger, BigInteger>();
		nMinusTwoCache = new HashMap<BigInteger, BigInteger>();
		fibCache.put(BigInteger.ZERO, BigInteger.ZERO);
		fibCache.put(BigInteger.ONE, BigInteger.ONE);
	}

	public BigInteger fib(BigInteger n) {
		if (fibCache.containsKey(n)) {
			return fibCache.get(n);
		} else {
			BigInteger leftFib = fib(cachedSubstractTwo(n));
			BigInteger rightFib = fib(cachedSubstractOne(n));

			BigInteger fibSum = leftFib.add(rightFib);
			fibCache.put(n, fibSum);

			return fibSum;
		}
	}

	private BigInteger cachedSubstractOne(BigInteger n) {
		return nMinusOneCache.computeIfAbsent(n, x -> x.subtract(BigInteger.ONE));
	}

	private BigInteger cachedSubstractTwo(BigInteger n) {
		return nMinusTwoCache.computeIfAbsent(n, x -> x.subtract(BigInteger.TWO));
	}

}
