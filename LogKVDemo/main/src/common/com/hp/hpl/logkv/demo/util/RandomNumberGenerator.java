package com.hp.hpl.logkv.demo.util;

import java.util.Random;

/**
 * Random number generator.
 * @author Edmond
 */
public final class RandomNumberGenerator {

	/**
	 * prevent to instance.
	 */
	private RandomNumberGenerator() {
	}

	/**
	 * random seed instance.
	 */
	private static final Random RAND = new Random();

	/**
	 * get random usage double value.
	 * @return	random usage double value
	 */
	public static double getRandomUsage() {
		return RAND.nextInt(10000) / 500.0;
	}

	/**
	 * get random usage double value.
	 * @return	random usage double value
	 */
	public static long getRandomLong() {
		return Math.abs(RAND.nextLong() % 10000);
	}

}
