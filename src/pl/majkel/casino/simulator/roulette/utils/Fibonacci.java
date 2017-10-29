package pl.majkel.casino.simulator.roulette.utils;

public class Fibonacci {
	public static Integer calculate(Integer n) {
		if (n <= 1)
			return n;
		else
			return Integer.valueOf(calculate(n - 1) + calculate(n - 2));
	}

	/**
	 * Oblicza numer obecnego ciagu fibonacciego bedacy levelem stawiania;
	 * @param value
	 * @return
	 */
	public static int calculateBetLevelForValue(int value){
		int i = 1;
		while(true){
			if(calculate(i) > value){
				return i;
			}
			i++;
		}


	}
}