package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;
import pl.majkel.casino.simulator.roulette.utils.Fibonacci;

public class FibonacciProgressionSystem extends RouletteSystem{

	public FibonacciProgressionSystem(int bank) {
		super(bank);
	}

	@Override
	public void nextBet(SpinResult spinResult) {
		if (bank - betValue > 0) {
			bank -= betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 12) {
				bank += betValue * 3;
				betValue = 1;
			} else {
				betLevel++;
				betValue = Fibonacci.calculate(betLevel);
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
		return "Progresja Fibonacciego";
	}

}
