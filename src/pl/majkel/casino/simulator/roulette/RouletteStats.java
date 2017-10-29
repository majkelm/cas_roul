package pl.majkel.casino.simulator.roulette;

public class RouletteStats {

	private long allSpins; 
	private long blackSpin; 
	private long redSpin;
	private long zeroSpin;
	private long firstDozen;
	private long secondDozen;
	private long thirdDozen;
	private int largestColorSeries;
	private int largestDozenSeries;
	private SpinResult lastSpin;
	private Dozen lastNotZeroDozen;
	
	public long getBlackSpin() {
		return blackSpin;
	}
	public void setBlackSpin(long blackSpin) {
		this.blackSpin = blackSpin;
	}
	public long getRedSpin() {
		return redSpin;
	}
	public void setRedSpin(long redSpin) {
		this.redSpin = redSpin;
	}
	public long getZeroSpin() {
		return zeroSpin;
	}
	public void setZeroSpin(long zeroSpin) {
		this.zeroSpin = zeroSpin;
	}
	public int getLargestColorSeries() {
		return largestColorSeries;
	}
	public void setLargestColorSeries(int largestColorSeries) {
		this.largestColorSeries = largestColorSeries;
	}
	public long getAllSpins() {
		return allSpins;
	}
	public void setAllSpins(long allSpins) {
		this.allSpins = allSpins;
	}
	public int getLargestDozenSeries() {
		return largestDozenSeries;
	}
	public void setLargestDozenSeries(int largestDozenSeries) {
		this.largestDozenSeries = largestDozenSeries;
	}
	public long getFirstDozen() {
		return firstDozen;
	}
	public void setFirstDozen(long firstDozen) {
		this.firstDozen = firstDozen;
	}
	public long getSecondDozen() {
		return secondDozen;
	}
	public void setSecondDozen(long secondDozen) {
		this.secondDozen = secondDozen;
	}
	public long getThirdDozen() {
		return thirdDozen;
	}
	public void setThirdDozen(long thirdDozen) {
		this.thirdDozen = thirdDozen;
	}
	public SpinResult getLastSpin() {
		return lastSpin;
	}
	public void setLastSpin(SpinResult lastSpin) {
		this.lastSpin = lastSpin;
	}
	public Dozen getLastNotZeroDozen() {
		return lastNotZeroDozen;
	}
	public void setLastNotZeroDozen(Dozen lastNotZeroDozen) {
		this.lastNotZeroDozen = lastNotZeroDozen;
	}
	
}
