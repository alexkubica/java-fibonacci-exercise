import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation with caching of recursive calls, bit integer's subtract and
 * additions. It's the most expensive memory wise.
 *
 */
public class AllCachedFibonacci implements Fibonacci {

	public HashMap<BigInteger, BigInteger> fibCache;
	public HashMap<BigInteger, BigInteger> nMinusOneCache;
	public HashMap<BigInteger, BigInteger> nMinusTwoCache;
	public HashMap<Set<BigInteger>, BigInteger> addCache;

	public AllCachedFibonacci() {
		fibCache = new HashMap<BigInteger, BigInteger>();
		nMinusOneCache = new HashMap<BigInteger, BigInteger>();
		nMinusTwoCache = new HashMap<BigInteger, BigInteger>();
		addCache = new HashMap<Set<BigInteger>, BigInteger>();
		fibCache.put(BigInteger.ZERO, BigInteger.ZERO);
		fibCache.put(BigInteger.ONE, BigInteger.ONE);
	}

	public BigInteger fib(BigInteger n) {
		if (fibCache.containsKey(n)) {
			return fibCache.get(n);
		} else {
			BigInteger leftFib = fib(cachedSubstractTwo(n));
			BigInteger rightFib = fib(cachedSubstractOne(n));

			BigInteger fibSum = cachedAdd(leftFib, rightFib);
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

	private BigInteger cachedAdd(BigInteger a, BigInteger b) {
		HashSet<BigInteger> pairToAdd = new HashSet<>();
		pairToAdd.add(a);
		pairToAdd.add(b);
		return addCache.computeIfAbsent(pairToAdd, pair -> {
			return a.add(b);
		});
	}

}
