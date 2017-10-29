package pl.majkel.casino.simulator.roulette;

public enum Dozen {
	FIRST_DOZEN, SECOND_DOZEN, THIRD_DOZEN, ZERO;
	
	public static Dozen valueOf(Integer number){
		if (number > 0 && number <= 12) {
			return Dozen.FIRST_DOZEN;
		}else if (number > 12 && number <= 24) {
			return Dozen.SECOND_DOZEN;
		} else if (number > 24 && number <= 36) {
			return Dozen.THIRD_DOZEN;
		} else{
			return Dozen.ZERO;
		}
	}
}