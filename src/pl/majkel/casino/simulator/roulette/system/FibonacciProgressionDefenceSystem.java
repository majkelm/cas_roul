package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;
import pl.majkel.casino.simulator.roulette.utils.Fibonacci;

public class FibonacciProgressionDefenceSystem extends RouletteSystem{

	public FibonacciProgressionDefenceSystem(int bank, int resignationPoint) {
		super(bank);
		this.resignationPoint = resignationPoint;
	}

	@Override
	public void nextBet(SpinResult spinResult) {
		cashBackIncreasingBank();
		if (bank - betValue > 0) {
			bank -= betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 12) {
				bank += betValue * 3;
				betValue = 1;
			} else {
				if(betLevel <= resignationPoint){
					betLevel++;
					betValue = Fibonacci.calculate(betLevel);
				} else {
					betLevel = 1;
					betValue = 1;
				}
			}
		} else {
			betValue = 1;
		}
	}
	
	@Override
	public void nextBet(SpinResult randNumber, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		throw new NotSupportedYetException();		
	}
	
	public String getName(){
		return "Progresja Fibonacciego defensywna";
	}

}
