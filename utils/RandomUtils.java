package com.dszi.utils;

import java.util.Random;

public class RandomUtils {
	static Random generator = new Random();

	public static int getRandomNumber() {
		return generator.nextInt(100);
	}
}
