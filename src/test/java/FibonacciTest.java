import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

	@Rule
	public TestName name = new TestName();
	public static List<Fibonacci> fibImplementations;
	public HashMap<Fibonacci, Long> executionTimes;

	@BeforeClass
	public static void setupBeforeAllTests() {
		// Initialize all implementations for Fibonacci
		fibImplementations = new ArrayList<>();
		fibImplementations.add(new AllCachedFibonacci());
		fibImplementations.add(new SimpleFibonacci());
		fibImplementations.add(new RecursiveCallCachedFibonacci());
		fibImplementations.add(new SubstractAndRecCallCachedFib());
	}

	@Before
	public void setupBeforeEachTest() {
		// Randomize implementations for Fibonacci so the runtime won't depend
		// on memory cache
		Collections.shuffle(fibImplementations);
		executionTimes = new HashMap<Fibonacci, Long>();
		System.out.println("Running test " + name.getMethodName());
		System.out.println("=================================");
	}

	@After
	public void afterEachTest() {
		printExecutionTimes();
		System.out.println();
	}

	@Test
	public void testFib0() {
		testFib(0, 0);
	}

	@Test
	public void testFib1() {
		testFib(1, 1);
	}

	@Test
	public void testFib2() {
		testFib(1, 2);
	}

	@Test
	public void testFib3() {
		testFib(2, 3);
	}

	@Test
	public void testFib4() {
		testFib(3, 4);
	}

	@Test
	public void testFib5() {
		testFib(5, 5);
	}

	@Test
	public void testBigNumbers() {
//		for (int i = 0; i < 10000000; i++) {
		// Got the numbers from the Internet
		testFib(832040, 30);
//			testFib(701408733, 44);
//			testFib(1134903170, 45);
//			testFib(433494437, 43);
//			testFib(1836311903, 46);
//		}
	}

	private void testFib(long expected, long input) {
		BigInteger expectedN = BigInteger.valueOf(expected);
		BigInteger inputN = BigInteger.valueOf(input);

		fibImplementations.forEach(fibImplementation -> {
			// Measure current implementation's execution time
			long start = System.currentTimeMillis();
			assertEquals(expectedN, fibImplementation.fib(inputN));
			long end = System.currentTimeMillis();
			executionTimes.put(fibImplementation, end - start);
		});
	}

	private void printExecutionTimes() {
		executionTimes.forEach((fib, time) -> {
			System.out.println(fib.getClass().getSimpleName() + " took " + time + "ms");
		});

		executionTimes.entrySet().stream().min((entryOne, entryTwo) -> {
			if (entryOne.getValue() >= entryTwo.getValue()) {
				return 1;
			} else {
				return -1;
			}
		}).ifPresent(fastestTime -> {
			System.out.println("Fastest implementation is " + fastestTime.getKey().getClass().getSimpleName());
		});

	}

}