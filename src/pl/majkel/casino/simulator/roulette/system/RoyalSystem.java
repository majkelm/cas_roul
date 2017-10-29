package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.Dozen;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class RoyalSystem extends RouletteSystem{

	public RoyalSystem(int bank) {
		super(bank);
	}
	
	public void nextBet(SpinResult spinResult) {
		cashBackIncreasingBank();
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 24) {
				bank += betValue * 3;
				if(startingBank < bank){
					startingBank = bank;
					betValue = 1;
					betLevel = 1;
				}
			} else {
				betValue++;
			}
		} else {
			betValue = 1;
		}
	}
	
	public String getName(){
		return "System królewski";
	}

	@Override
	public void nextBet(SpinResult spinResult, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		cashBackIncreasingBank();
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getDozen() != Dozen.ZERO && spinResult.getDozen() != stats.getLastNotZeroDozen()) {
				bank += betValue * 3;
			} else {
				//betValue++;
			}
		} else {
			betValue = 1;
		}		
	}

}
