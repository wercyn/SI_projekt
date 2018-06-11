package com.dszi.logikaklauzul;

import com.dszi.firstorderlogic.Operators;

public class LogikaKlauzul extends Operators {

	public boolean KPN(boolean p, boolean... chars) {
		for (boolean q : chars) {
			if (Conjunction(p, q) == true && q == true)
				p = true;
			if (Conjunction(p, q) == true && q == false)
				p = false;
			if (Conjunction(p, q) == false && q == true)
				p = false;
			if (Conjunction(p, q) == false && q == false)
				p = false;
		}
		return p;
	}

	public boolean APN(boolean p, boolean... chars) {
		for (boolean q : chars) {
			if (Alternative(p, q) == true && q == true)
				p = true;
			if (Alternative(p, q) == true && q == false)
				p = false;
			if (Alternative(p, q) == false && q == true)
				p = true;
			if (Alternative(p, q) == false && q == false)
				p = true;
		}
		return p;
	}
}
