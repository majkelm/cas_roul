package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class System63Defence extends RouletteSystem{
	
	
	public System63Defence(int bank, int resignationPoint) {
		super(bank);
		this.resignationPoint = resignationPoint;
	}
	
	public void nextBet(SpinResult spinResult) {
		cashBackIncreasingBank();
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 24) {
				bank += betValue * 3;
				betValue = 1;
			} else {
				if(betLevel <= resignationPoint){
					betValue = betValue * 3;
					betLevel++;
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
		return "System 63 defensywny";
	}
}
