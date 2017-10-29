package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class DelambertSystem extends RouletteSystem {

	public DelambertSystem(int bank) {
		super(bank);
	}

	@Override
	public void nextBet(SpinResult spinResult) {
		cashBackIncreasingBank();
		if (bank - betValue > 0) {
			bank -= betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 18) {
				bank += betValue * 2;
				if (betValue >= 1) {
					betValue -= 1;
				}
			} else {
				betValue += 1;
			}
		}
	}

	@Override
	public void nextBet(SpinResult randNumber, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		throw new NotSupportedYetException();
	}

	public String getName() {
		return "System Delamberta";
	}

}
