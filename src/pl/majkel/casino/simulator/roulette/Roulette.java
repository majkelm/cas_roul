package pl.majkel.casino.simulator.roulette;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Roulette {

	List<SpinResult> history = new LinkedList<SpinResult>();
	private int largestColorSeries = 1;
	private int largestDozenSeries = 1;
	private int colorSeriesCounter = 1;
	private int dozenSeriesCounter = 1;

	Random generator = new Random();
	int randNumber;

	public SpinResult makeSpin() {
		randNumber = generator.nextInt(36);
		// history.add
		return new SpinResult(randNumber);
	}

	public SpinResult makeSpinAndSaveResult() {
		randNumber = generator.nextInt(36);
		SpinResult result = new SpinResult(randNumber);
		// Najdluzsza seria jednego koloru
		if (history.size() >= 1 && result.getColor().equals(history.get(history.size() - 1).getColor())) {
			colorSeriesCounter++;
		} else {
			colorSeriesCounter = 1;
		}
		if (colorSeriesCounter > largestColorSeries) {
			largestColorSeries = colorSeriesCounter;
		}
		// Najdluzsza seria jednego tuzina
		if (history.size() >= 1 && result.getDozen().equals(history.get(history.size() - 1).getDozen())) {
			dozenSeriesCounter++;
		} else {
			dozenSeriesCounter = 1;
		}
		if (dozenSeriesCounter > largestDozenSeries) {
			largestDozenSeries = dozenSeriesCounter;
		}
		history.add(result);
		return result;
	}

	public RouletteStats getCalculatedStats() {
		long blackSpins = history.stream().filter(p -> p.getColor().equals(Color.BLACK)).count();
		long redSpins = history.stream().filter(p -> p.getColor().equals(Color.RED)).count();
		long zeroSpins = history.stream().filter(p -> p.getColor().equals(Color.GREEN)).count();
		long firstDozen = history.stream().filter(p -> p.getDozen().equals(Dozen.FIRST_DOZEN)).count();
		long secondDozen = history.stream().filter(p -> p.getDozen().equals(Dozen.SECOND_DOZEN)).count();
		long thirdDozen = history.stream().filter(p -> p.getDozen().equals(Dozen.THIRD_DOZEN)).count();
		RouletteStats stats = new RouletteStats();
		stats.setBlackSpin(blackSpins);
		stats.setRedSpin(redSpins);
		stats.setZeroSpin(zeroSpins);
		stats.setFirstDozen(firstDozen);
		stats.setSecondDozen(secondDozen);
		stats.setThirdDozen(thirdDozen);
		stats.setLargestColorSeries(largestColorSeries);
		stats.setLargestDozenSeries(largestDozenSeries);
		return stats;
	}

	public RouletteStats getCalculatedStats(int numberOfSpins) {
		List<SpinResult> historyChosen = history.subList(
				history.size() - numberOfSpins - 1 > 0 ? history.size() - numberOfSpins - 1 : 0, history.size() - 1);
		long blackSpins = historyChosen.stream().filter(p -> p.getColor().equals(Color.BLACK)).count();
		long redSpins = historyChosen.stream().filter(p -> p.getColor().equals(Color.RED)).count();
		long zeroSpins = historyChosen.stream().filter(p -> p.getColor().equals(Color.GREEN)).count();
		Dozen lastNotZeroDozen = null;
		for(SpinResult spin : historyChosen){
			if(spin.getDozen() != Dozen.ZERO){
				lastNotZeroDozen = spin.getDozen();
			}
		}

		RouletteStats stats = new RouletteStats();
		stats.setAllSpins(history.size());
		stats.setBlackSpin(blackSpins);
		stats.setRedSpin(redSpins);
		stats.setZeroSpin(zeroSpins);
		stats.setLargestColorSeries(largestColorSeries);
		stats.setLargestDozenSeries(largestDozenSeries);
		stats.setLastNotZeroDozen(lastNotZeroDozen);
		if (history.size() > 1) {
			stats.setLastSpin(history.get(history.size() - 2));
		}
		return stats;
	}

}
