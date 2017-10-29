package pl.majkel.casino.simulator.roulette;

import java.util.Arrays;
import java.util.List;

public enum Color {
	BLACK,
	RED,
	GREEN;
	
	public static final List<Integer> BLACK_FIELD_NUMBERS = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
	public static final List<Integer> RED_FIELD_NUMBERS = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
	
	public static Color valueOf(Integer number){
		if(BLACK_FIELD_NUMBERS.contains(number)){
			return BLACK;
		} else if(RED_FIELD_NUMBERS.contains(number)){
			return RED; 
		} else {
			return GREEN;
		}
	}
}
