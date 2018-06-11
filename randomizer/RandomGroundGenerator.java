package com.dszi.randomizer;

import com.dszi.utils.RandomUtils;

public class RandomGroundGenerator {

	public static int generateIrrigation() {
		return RandomUtils.getRandomNumber();
	}

	public static int generateSoilDesctruction() {
		return RandomUtils.getRandomNumber();
	}

	public static int generateNumberOfPests() {
		return RandomUtils.getRandomNumber();
	}
}
