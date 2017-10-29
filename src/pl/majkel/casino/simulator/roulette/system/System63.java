package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class System63 extends RouletteSystem{

	public System63(int bank) {
		super(bank);
	}
	
	public void nextBet(SpinResult spinResult) {
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 24) {
				bank += betValue * 3;
				betValue = 1;
			} else {
				betValue = betValue * 3;
			}
		} else {
			betValue = 1;
		}
	}
	
	public String getName(){
		return "System 63";
	}

	@Override
	public void nextBet(SpinResult randNumber, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		throw new NotSupportedYetException();		
	}

}
