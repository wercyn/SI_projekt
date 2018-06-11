package com.dszi.firstorderlogic;

public class Operators {
	public boolean Conjunction(boolean p, boolean q) {
		boolean result = false;
		if (p == true && q == true)
			result = true;
		if (p == true && q == false)
			result = false;
		if (p == false && q == true)
			result = false;
		if (p == false && q == false)
			result = false;
		return result;
	}

	public boolean Alternative(boolean p, boolean q) {
		boolean result = false;
		if (p == true && q == true)
			result = true;
		if (p == true && q == false)
			result = true;
		if (p == false && q == true)
			result = true;
		if (p == false && q == false)
			result = false;
		return result;
	}

	public boolean Implication(boolean p, boolean q) {
		boolean result = false;
		if (p == true && q == true)
			result = true;
		if (p == true && q == false)
			result = false;
		if (p == false && q == true)
			result = true;
		if (p == false && q == false)
			result = true;
		return result;
	}

	public boolean Equity(boolean p, boolean q) {
		boolean result = false;
		if (p == true && q == true)
			result = true;
		if (p == true && q == false)
			result = false;
		if (p == false && q == true)
			result = false;
		if (p == false && q == false)
			result = true;
		return result;
	}
}
