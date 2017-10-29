package pl.majkel.casino.simulator.roulette;

public class SpinResult {

	private Integer number;
	private Color color;
	/**
	 * Tuzin
	 */
	private Dozen dozen;
	/**
	 * Czy parzysty
	 */
	private boolean even;
	
	public SpinResult(Integer number){
		this.number = number;
		this.color = Color.valueOf(number);
		this.dozen = Dozen.valueOf(number);
		this.even = number % 2 == 0;
	}
	
	public Integer getNumber() {
		return number;
	}
	public Color getColor() {
		return color;
	}
	public Dozen getDozen() {
		return dozen;
	}
	public boolean isEven() {
		return even;
	}
		
}
