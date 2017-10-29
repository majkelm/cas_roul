package pl.majkel.casino.simulator;

import pl.majkel.casino.simulator.roulette.Roulette;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;
import pl.majkel.casino.simulator.roulette.system.RouletteSystem;

public class RouletteSimulator {

	private Roulette roulette = new Roulette();
	private Integer largestBankValue = 0;
	private Integer lessBankValue = 0;
	
	private RouletteSystem system;
	
	public RouletteSimulator(RouletteSystem system){
		this.system = system;
	}
	
	public void simulateContinouslyGame(int spinCount) throws BankrutException {
		for (int i = 1; i < spinCount; i++) {
			system.nextBet(roulette.makeSpinAndSaveResult());
			if(system.getBank() > largestBankValue){
				this.largestBankValue = system.getBank();
			}
		}
	}
	
	
	public void simulateContinouslyGameDependsOnStats(int spinCount, int lastSpinStats) throws BankrutException, NotSupportedYetException {
		for (int i = 1; i < spinCount; i++) {
			system.nextBet(roulette.makeSpinAndSaveResult(), roulette.getCalculatedStats(lastSpinStats));
			if(system.getBank() > largestBankValue){
				this.largestBankValue = system.getBank();
			}
			if(system.getBank() < lessBankValue){
				this.lessBankValue = system.getBank();
			}
		}
	}

	public void simulateMontlyGame(int spinCount, int monthNumber) throws BankrutException {
		for (int k = 1; k < monthNumber; k++) {
			for (int i = 1; i < spinCount; i++) {
				system.nextBet(roulette.makeSpinAndSaveResult());
			}
		}
	}
	
	public void printStats(){
		RouletteStats stats = roulette.getCalculatedStats();
		
		long allSpins = stats.getBlackSpin() + stats.getRedSpin() + stats.getZeroSpin();
		System.out.println("Czarne: " + ((double)stats.getBlackSpin()/allSpins)*100 + " %");
		System.out.println("Czerwone: " + ((double)stats.getRedSpin()/allSpins)*100 + " %");
		System.out.println("Zero: " + ((double)stats.getZeroSpin()/allSpins)*100 + " %");
		// ostatnie 50 spinow
		stats = roulette.getCalculatedStats(50);
		System.out.println("Czarne: " + ((double)stats.getBlackSpin()/50)*100 + " %");
		System.out.println("Czerwone: " + ((double)stats.getRedSpin()/50)*100 + " %");
		System.out.println("Zero: " + ((double)stats.getZeroSpin()/50)*100 + " %");

		
		
		System.out.println("Nad³u¿sza seria jednego koloru: " + stats.getLargestColorSeries());
		System.out.println("Nad³u¿sza seria jednego tuzina: " + stats.getLargestDozenSeries());
	}

	public void setSystem(RouletteSystem system) {
		this.system = system;
	}

	public Integer getLargestBankValue() {
		return largestBankValue;
	}

	public Integer getLessBankValue() {
		return lessBankValue;
	}
	
	
	
}
