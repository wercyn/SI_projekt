package com.dszi.logikaklauzul;

import com.dszi.gui.SingleCell;

public class LogicProperties {
	public static boolean TooGoodForGenetic(SingleCell s) {

		LogikaKlauzul c = new LogikaKlauzul();
		boolean result = false;
		boolean water = false;
		boolean pesticides = false;
		boolean damage = false;

		if (s.getIrrigation() > 95)
			water = true;
		if (s.getNumberOfPests() < 5)
			pesticides = true;
		if (s.getSoilDestruction() < 5)
			damage = true;
		if (c.KPN(water, pesticides, damage) == true)
			result = true;

		return result;
	}

	public static boolean GeneticAlgorithmMayRefuseThisCell(SingleCell s) {

		LogikaKlauzul c = new LogikaKlauzul();
		boolean result = false;
		boolean water = false;
		boolean pesticides = false;
		boolean damage = false;

		if (s.getIrrigation() < 50)
			water = true;
		if (s.getNumberOfPests() > 50)
			pesticides = true;
		if (s.getSoilDestruction() > 50)
			damage = true;
		if (c.APN(c.KPN(water, pesticides), c.KPN(water, damage), c.KPN(pesticides, damage)))
			result = true;

		return result;

	}
}
